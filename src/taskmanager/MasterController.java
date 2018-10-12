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

    @FXML private JFXListView list;
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

//TODO Create one big partitioned table to store tasks
//TODO Implement delete task function
//TODO Implement delete user function
//TODO When each user logs on he sees his own tasks loaded from initialize function
//TODO Implement relative file paths so the application works everywhere
//TODO Teachers pick students from database to set their tasks to
//TODO Teachers can see which students have done what tasks
//TODO Calendar screen for students