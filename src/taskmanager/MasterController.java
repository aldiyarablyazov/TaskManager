package taskmanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MasterController implements Initializable {

    @FXML public JFXListView list;
    @FXML private JFXButton logOutButton;

    @FXML
    private void logOut(ActionEvent event) {
        Utilities.changeScene(new MasterController(), logOutButton, "LoginGUI.fxml", 700, 400);
    }

    public void addTask(String task) {
        ObservableList<String> items = FXCollections.observableArrayList (task);
        list.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTask("No tasks set");
    }
}