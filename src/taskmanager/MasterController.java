package taskmanager;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class MasterController implements Initializable {

    @FXML private JFXButton logOutButton;
    @FXML private TableView tasksTableView;
    @FXML private TableColumn<TableList, String> taskColumn;
    @FXML private TableColumn<TableList, String> subjectColumn;
    @FXML private TableColumn<TableList, LocalDate> dueDateColumn;
    @FXML private TableColumn<TableList, String> completedColumn;

    @FXML
    private void logOut(ActionEvent event) {
        Utilities.changeScene(new MasterController(), logOutButton, "LoginGUI.fxml", 700, 400);
    }

    @FXML public void quit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        taskColumn.setCellValueFactory(new PropertyValueFactory<TableList,String>("Task"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<TableList,String>("Subject"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<TableList,LocalDate>("DueDate"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<TableList,String>("Completed"));

        tasksTableView.setItems(getTasks());
    }

    public ObservableList<TableList> getTasks() {
        ObservableList<TableList> data = FXCollections.observableArrayList();
        data.add(new TableList("Finish your math IA", "Mathematics HL",LocalDate.of(2018, Month.NOVEMBER, 6)));
        data.add(new TableList("Give in your university applications Give in your university applications Give in your university applications Give in your university applications Give in your university applications ", "Computer Science",LocalDate.of(2018, Month.JANUARY, 1)));
        data.add(new TableList("Complete your Computer Science IA", "Computer Science",LocalDate.of(2018, Month.DECEMBER, 1)));
        return data;
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