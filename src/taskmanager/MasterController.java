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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MasterController implements Initializable {

    @FXML private JFXButton logOutButton;
    @FXML private TableView tasksTableView;
    @FXML private TableColumn<TableList, String> taskColumn;
    @FXML private TableColumn<TableList, String> subjectColumn;
    @FXML private TableColumn<TableList, LocalDate> dueDateColumn;
    @FXML private StackPane dialogStackPane;

    TaskController taskController = new TaskController();
    String currentSelection;

    ArrayList taskIDs = taskController.getTaskIDs();
    ArrayList studentIDs = taskController.getStudentIDs();
    ArrayList taskTitles = taskController.getTaskTitles();
    ArrayList descriptions = taskController.getDescriptions();
    ArrayList subjects = taskController.getSubjects();
    ArrayList isCompleteds = taskController.getIsCompleteds();
    ArrayList<java.sql.Date> dueDates = taskController.getDueDates();
    ArrayList currentDesc = new ArrayList();

    @FXML
    private void logOut(ActionEvent event) {
        Utilities.changeScene(new MasterController(), logOutButton, "LoginGUI.fxml", 700, 400);
    }

    @FXML public void quit() {
        System.exit(0);
    }

    public void loadDialog(String title, String description) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Label(title));
        content.setBody(new Label(description));
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

        dialogStackPane.setPickOnBounds(false);

        // Listens for double click on row
        tasksTableView.setRowFactory( tv -> {

            TableRow<TableList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    currentDesc.clear();
                    TableList rowData = row.getItem();
                    for (int i = 0; i < taskIDs.size(); i++) {
                        if (taskTitles.get(i).equals(rowData.getTask())) {
                            currentDesc.add(descriptions.get(i));
                        }
                    }
                    loadDialog(rowData.getTask(), (String) currentDesc.get(currentDesc.size()-1));
                } else if (event.getClickCount() == 1 && (! row.isEmpty())) {
                    TableList rowData = row.getItem();
                    currentSelection = rowData.getTask();
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
                if (studentIDs.get(i).equals(getCurrentUser()) && isCompleteds.get(i).equals(0)) {
                    data.add(new TableList((String) taskTitles.get(i), (String) subjects.get(i), dueDates.get(i).toLocalDate()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @FXML
    public void setAsCompleted() {
        tasksTableView.getItems().removeAll(tasksTableView.getSelectionModel().getSelectedItems());

        try {

            Object dueTask = tasksTableView.getSelectionModel().getSelectedItem();
            System.out.println(dueTask);

            Connection dbConn = DriverManager.getConnection("jdbc:mysql://remote-mysql3.servage.net:3306/alexa", "alexa", "Alex2018");
            Statement statement = dbConn.createStatement();

            statement.execute("UPDATE alexa.Tasks SET IsCompleted = 1 WHERE StudentID = '" + getCurrentUser() + "' AND TaskTitle = '" + currentSelection + "'");

            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentUser() {
        return CurrentUser.getCurrentUser();
    }

}