package taskmanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
    @FXML private StackPane dialogStackPane;

    TaskController taskController = new TaskController();

    ArrayList taskIDs = taskController.getTaskIDs();
    ArrayList studentIDs = taskController.getStudentIDs();
    ArrayList taskTitles = taskController.getTaskTitles();
    ArrayList descriptions = taskController.getDescriptions();
    ArrayList subjects = taskController.getSubjects();
    ArrayList<java.sql.Date> dueDates = taskController.getDueDates();


    @FXML
    private void logOut(ActionEvent event) {
        Utilities.changeScene(new MasterController(), logOutButton, "LoginGUI.fxml", 700, 400);
    }

    @FXML public void quit() {
        System.exit(0);
    }

    public void loadDialog(String title, String description) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        content.setBody(new Text(description));

        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton doneButton = new JFXButton("Done");
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(doneButton);

        dialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        taskColumn.setCellValueFactory(new PropertyValueFactory<TableList,String>("Task"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<TableList,String>("Subject"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<TableList,LocalDate>("DueDate"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<TableList,String>("Completed"));

        dialogStackPane.setPickOnBounds(false);
        ArrayList currentDesc = new ArrayList();


        // Listens for double click on row
        tasksTableView.setRowFactory( tv -> {
            currentDesc.clear();
            TableRow<TableList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    TableList rowData = row.getItem();
                    for (int i = 0; i < taskIDs.size(); i++) {
                        if (taskTitles.get(i).equals(rowData.getTask())) {
                            currentDesc.add((String) descriptions.get(i));
                        }
                    }
                    loadDialog(rowData.getTask(), (String) currentDesc.get(currentDesc.size()-1));

                }
            });

            return row ;
        });

        tasksTableView.setItems(getTasks());
    }

    public ObservableList<TableList> getTasks() {

        ObservableList<TableList> data = FXCollections.observableArrayList();

        try {

            taskController.taskInit();
            for (int i = 0; i < taskIDs.size(); i++) {
                if (studentIDs.get(i).equals(getCurrentUser())) {
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

    public TableView getTableView() {
        return tasksTableView;
    }
}

//TODO Implement delete task function
//TODO Implement relative file paths so the application works everywhere
//TODO Teachers can see which students have done what tasks
//TODO Calendar screen for students