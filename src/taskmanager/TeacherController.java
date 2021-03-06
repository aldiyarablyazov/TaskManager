package taskmanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TeacherController implements Initializable {

    @FXML private JFXTextArea taskDescription;
    @FXML private JFXTextField taskTitle;
    @FXML private JFXDatePicker dueDate;
    @FXML private JFXComboBox subjectSelect;
    @FXML private TableView studentTableView;
    @FXML private TableColumn<StudentTableView, String> selectColumn;
    @FXML private TableColumn<StudentTableView, String> studentColumn;

    @FXML private TableView studentsDueTableView;
    @FXML private TableColumn<DueTasksTableView, String> subjectDueColumn;
    @FXML private TableColumn<DueTasksTableView, String> studentDueColumn;
    @FXML private TableColumn<DueTasksTableView, String> taskDueColumn;
    @FXML private TableColumn<DueTasksTableView, String> statusDueColumn;

    @FXML public void quit() {
        System.exit(0);
    }

    @FXML public void logOut() {
        Utilities.changeScene(new MasterController(), studentTableView, "LoginGUI.fxml", 700, 400);
    }

    public static String getCurrentUser() {
        return CurrentUser.getCurrentUser();
    }

    @FXML public void setTaskPressed() throws SQLException {

        ArrayList setTaskForStudents = new ArrayList();

        // adds all selected (checkedboxed) students to an setTaskForStudents ArrayList
        for (StudentTableView student: students) {
            if(student.getSelect().isSelected()) {
                setTaskForStudents.add(student.getStudentName());
            }
        }

        String description = taskDescription.getText();
        String title = taskTitle.getText();
        String subject = (String) subjectSelect.getValue();

        // Error handling
        if ("".equals(description)) {
            taskDescription.setUnFocusColor(Color.RED);
        } else if ("".equals(title)) {
            taskTitle.setUnFocusColor(Color.RED);
        } else if (subject == null) {
            subjectSelect.setUnFocusColor(Color.RED);
        } else if (dueDate.getValue() == null) {
            dueDate.setDefaultColor(Color.RED);
        } else {
            DatabaseController databaseController = new DatabaseController();
            databaseController.handleConnect();

            taskDescription.setUnFocusColor(Color.web("#4d4d4d"));
            taskTitle.setUnFocusColor(Color.web("#4d4d4d"));
            subjectSelect.setUnFocusColor(Color.web("#4d4d4d"));
            dueDate.setDefaultColor(Color.web("#4d4d4d"));

            // Adds task to database for all setTaskForStudents members
            for (int i = 0; i < setTaskForStudents.size(); i++) {
                Connection dbConn = DriverManager.getConnection("jdbc:mysql://remote-mysql3.servage.net:3306/alexa", "alexa", "Alex2018");
                Statement statement = dbConn.createStatement();
                LoginController loginController = new LoginController();

                statement.execute("INSERT INTO alexa.Tasks SET "
                        + "studentID = '" + setTaskForStudents.get(i) + "', "
                        + "taskTitle = '" + title + "', "
                        + "subject = '" + subject + "', "
                        + "description = '" + description + "', "
                        + "teacher = '" + getCurrentUser() + "', "
                        + "dueDate = '" + dueDate.getValue() + "', "
                        + "isCompleted = " + 0);
                dbConn.close();

            }
        }
    }

    // method returns an ObservableList of all students in the database

    DatabaseController databaseController = new DatabaseController();
    ArrayList<String> usernames = databaseController.getUsernames();
    ArrayList isTeachers = databaseController.getProfession();
    final ObservableList<StudentTableView> students = FXCollections.observableArrayList();

    public ObservableList<StudentTableView> getStudents() throws SQLException {
        databaseController.handleConnect();
        for (int i = 0; i < (usernames.size()); i++) {
            if (isTeachers.get(i).equals(0)) {
                students.add(new StudentTableView(usernames.get(i)));
            }
        }
        return students;
    }

    TaskController taskController = new TaskController();
    ArrayList subjetsDue = taskController.getSubjects();
    ArrayList studentsDue = taskController.getStudentIDs();
    ArrayList teachersSet = taskController.getTeachers();
    ArrayList titlesDue = taskController.getTaskTitles();
    ArrayList statusDue = taskController.getIsCompleteds();
    final ObservableList<DueTasksTableView> currentTeacherStudentsDue = FXCollections.observableArrayList();

    public ObservableList<DueTasksTableView> getStudentsDue() throws SQLException {
        taskController.taskInit();
        for (int i = 0; i < (studentsDue.size()); i++) {
            if (teachersSet.get(i).equals(getCurrentUser())) {
                currentTeacherStudentsDue.add(new DueTasksTableView((String) subjetsDue.get(i), (String) studentsDue.get(i), (String) titlesDue.get(i), (int) statusDue.get(i)));
            }
        }
        return currentTeacherStudentsDue;
    }

    @Override
        public void initialize(URL location, ResourceBundle resources) {

        // Task setting TableView setup


        selectColumn.setCellValueFactory(new PropertyValueFactory<StudentTableView, String>("Select"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<StudentTableView, String>("studentName"));

        try {
            studentTableView.setItems(getStudents());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Due tasks TableView setup

        subjectDueColumn.setCellValueFactory(new PropertyValueFactory<DueTasksTableView, String>("subject"));
        studentDueColumn.setCellValueFactory(new PropertyValueFactory<DueTasksTableView, String>("studentName"));
        taskDueColumn.setCellValueFactory(new PropertyValueFactory<DueTasksTableView, String>("task"));
        statusDueColumn.setCellValueFactory(new PropertyValueFactory<DueTasksTableView, String>("isCompleted"));

        try {
            studentsDueTableView.setItems(getStudentsDue());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        subjectSelect.getItems().addAll(
                "Mathematics",
                "Biology",
                "Physics",
                "Chemistry",
                "Computer Science",
                "English",
                "Spanish",
                "French",
                "History",
                "Geography",
                "Economics",
                "Business Management",
                "ESS",
                "Art",
                "Music",
                "Drama"
        );

    }



}