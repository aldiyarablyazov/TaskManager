package taskmanager;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private  JFXTextField usernameField;
    @FXML private JFXPasswordField passwordField;
    @FXML private JFXTextField usernameField1;
    @FXML private JFXPasswordField passwordField1;
    @FXML private JFXPasswordField repeatPasswordField;
    @FXML private JFXRadioButton studentRadio1;
    @FXML private JFXRadioButton teacherRadio1;
    @FXML private JFXRadioButton studentRadio2;
    @FXML private JFXRadioButton teacherRadio2;
    @FXML private JFXTabPane loginTabPane;
    @FXML private Tab registerTab;
    @FXML private JFXButton loginButton;
    @FXML private Tab loginTab;
    @FXML private Label loginErrorLabel;
    @FXML private Label registerStatus;

    @FXML
    private void loginButtonPressed(ActionEvent event) throws SQLException, IOException {
        System.out.println("\n");
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Radio Button Error Handling
        int isTeacher = 0;

        if (studentRadio1.isSelected()) {
            isTeacher = 0;
        } else if (teacherRadio1.isSelected()) {
            isTeacher = 1;
        } else {
            System.out.println("You have to select your profession");
        }

        // Text Field Error Handling
        if ("".equals(username)) {
            System.out.println("You must enter a username");
            usernameField.setStyle("-fx-prompt-text-fill: red");
        } else if ("".equals(password)) {
            System.out.println("You must enter a password");
            passwordField.setStyle("-fx-prompt-text-fill: red");
        } else {

            usernameField.setStyle("-fx-text-inner-color: white");
            passwordField.setStyle("-fx-text-inner-color: white");

            AccountController accountController = new AccountController();
            if (accountController.isValidAccount(usernameField.getText(), passwordField.getText(), isTeacher)) {
                if (isTeacher==0) {
                    Utilities.changeScene(new MasterController(), loginButton, "MasterGUI.fxml", 1050, 600);
                } else {
                    Utilities.changeScene(new MasterController(), loginButton, "TeacherGUI.fxml", 1050, 600);
                }
                loginErrorLabel.setVisible(false);
            } else {
                loginErrorLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void registerButtonPressed(ActionEvent event) throws SQLException,IOException {
        // Text Field Error Handling
        String username1 = usernameField1.getText();
        String password1 = passwordField1.getText();
        String repeatPass = repeatPasswordField.getText();

        int isTeacher = -1;
        if (studentRadio2.isSelected()) {
            isTeacher = 0;
        } else if (teacherRadio2.isSelected()) {
            isTeacher = 1;
        }

        if (isTeacher == -1) {
            registerStatus.setText("You must enter a profession");
            usernameField1.setStyle("-fx-prompt-text-fill: red");
            registerStatus.setVisible(true);

        } else if ("".equals(username1)) {
            usernameField1.setStyle("-fx-prompt-text-fill: red");
            registerStatus.setText("You must enter a username");
            usernameField1.setStyle("-fx-prompt-text-fill: red");
            registerStatus.setVisible(true);

        } else if ("".equals(password1)) {
            registerStatus.setText("You must enter a password");
            usernameField1.setStyle("-fx-prompt-text-fill: red");
            registerStatus.setVisible(true);

        } else if ("".equals(repeatPass)) {
            registerStatus.setText("You must repeat your password");
            usernameField1.setStyle("-fx-prompt-text-fill: red");
            registerStatus.setVisible(true);

        } else if (!password1.equals(repeatPass)) {
            registerStatus.setText("Passwords do not match");
            usernameField1.setStyle("-fx-prompt-text-fill: red");
            registerStatus.setVisible(true);

        }  else {
            // Checks if there isn't a duplicate, it adds to database and returns false, otherwise returns true
            DatabaseController databaseController = new DatabaseController();
            Boolean duplicateUsername = databaseController.handleRegisterConnect(username1, password1, isTeacher);

            if (duplicateUsername == false) {
                usernameField.setStyle("-fx-text-inner-color: white");
                registerStatus.setText("Account creation successful");
                registerStatus.setVisible(true);

            } else {
                registerStatus.setStyle("-fx-prompt-text-fill: red");
                registerStatus.setText("This username already exists");
                registerStatus.setVisible(true);
            }

        }
    }

    @FXML
    private void goToRegisterButtonPressed(ActionEvent event) {
        loginTabPane.getSelectionModel().select(registerTab);
    }

    @FXML
    private void goToLoginButtonPressed(ActionEvent event) {
        loginTabPane.getSelectionModel().select(loginTab);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameField.setStyle("-fx-text-inner-color: white");
        passwordField.setStyle("-fx-text-inner-color: white");
    }

}
