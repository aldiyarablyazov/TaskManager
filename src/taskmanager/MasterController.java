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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MasterController implements Initializable {

    @FXML private JFXButton logOutButton;
    @FXML private TableView tasksTableView;
    @FXML private TableColumn<TableList, String> taskColumn;
    @FXML private TableColumn<TableList, String> subjectColumn;
    @FXML private TableColumn<TableList, LocalDate> dueDateColumn;
    @FXML private TableColumn<TableList, String> completedColumn;

    TaskController taskController = new TaskController();
    ArrayList currentUserTasks = new ArrayList<>();

    ArrayList taskIDs = taskController.getTaskIDs();
    ArrayList studentIDs = taskController.getStudentIDs();
    ArrayList taskTitles = taskController.getTaskTitles();
    ArrayList subjects = taskController.getSubjects();
    ArrayList descriptions = taskController.getDescriptions();
    ArrayList teachers = taskController.getTeachers();
    ArrayList<java.sql.Date> dueDates = taskController.getDueDates();
    ArrayList isCompleteds = taskController.getIsCompleteds();


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

        try {

            taskController.taskInit();
            for (int i = 0; i < taskIDs.size(); i++) {
                System.out.println("\n");
                if (studentIDs.get(i).equals(getCurrentUser())) {
                    currentUserTasks.add(taskIDs.get(i));
                    data.add(new TableList((String) taskTitles.get(i), (String) subjects.get(i), dueDates.get(i).toLocalDate()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return data;

    }


    public static String getCurrentUser() {
        return CurrentUser.getCurrentUser();
    }
}

//TODO Implement delete task function
//TODO Implement relative file paths so the application works everywhere
//TODO Teachers can see which students have done what tasks
//TODO Calendar screen for students