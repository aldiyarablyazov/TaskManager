package taskmanager;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private JFXTextField usernameField;
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

    private ArrayList usernames = new ArrayList();
    private ArrayList passwords = new ArrayList();
    private ArrayList professions = new ArrayList();
    private ArrayList userIDs = new ArrayList();

    @FXML
    private void loginButtonPressed(ActionEvent event) {
        System.out.println("\n");
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Radio Button Error Handling
        String selected = "";
        if (studentRadio1.isSelected() == true) {
            selected = "Student";
        } else if (teacherRadio1.isSelected() == true) {
            selected = "Teacher";
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

            System.out.println("Position: " + selected);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);



            if (AccountController.isValidAccount()) {
                Utilities.changeScene(new MasterController(), loginButton, "MasterGUI.fxml");
            } else {
                //invalid account

            }


        }

    }

    @FXML
    private void registerButtonPressed(ActionEvent event) {
        // Text Field Error Handling
        String username1 = usernameField1.getText();
        String password1 = passwordField1.getText();
        String repeatPass = repeatPasswordField.getText();

        String selectedProfession = "";
        if (studentRadio2.isSelected() == true) {
            selectedProfession = "Student";
        } else if (teacherRadio2.isSelected() == true) {
            selectedProfession = "Teacher";
        }

        if ("".equals(selectedProfession)) {
            System.out.println("You must enter a profession");
        } else if ("".equals(username1)) {
            System.out.println("You must enter a username");
            usernameField1.setStyle("-fx-prompt-text-fill: red");

        } else if ("".equals(password1)) {
            System.out.println("You must enter a password");
            passwordField1.setStyle("-fx-prompt-text-fill: red");

        } else if ("".equals(repeatPass)) {
            System.out.println("You must repeat your password");
            repeatPasswordField.setStyle("-fx-prompt-text-fill: red");

        } else if (!password1.equals(repeatPass)) {
            System.out.println("Passwords do not match");
            repeatPasswordField.setStyle("-fx-prompt-text-fill: red");

        }  else {
            System.out.println("\n");
            System.out.println("Position: " + selectedProfession);
            System.out.println("Username: " + username1);
            System.out.println("Password: " + password1);

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
