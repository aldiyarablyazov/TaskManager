package taskmanager;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    @FXML private JFXListView studentNamesListView;
    @FXML private JFXButton setTaskButton;
    @FXML private JFXTextArea taskDescription;
    @FXML private JFXTextField taskTitle;
    @FXML private JFXDatePicker dueDate;
    @FXML private JFXComboBox subjectSelect;
    @FXML private TableColumn selectColumn;
    @FXML private TableColumn studentsColumn;
    @FXML private TableView studentTableView;

    /*

    DatabaseController databaseController = new DatabaseController();

    ArrayList<String> studentArray = new ArrayList();
    ArrayList<String> usernames = databaseController.getUsernames();
    ArrayList isTeachers = databaseController.getProfession();

    public void getStudents() throws SQLException { // adds all students in DB to studentArray
        databaseController.handleConnect();

        for (int i = 0; i < (usernames.size()); i++) {
            if (isTeachers.get(i).equals(0)) {
                studentArray.add(usernames.get(i));
            }
        }

    }

    */

    /*
    public void addStudents() {
        ObservableList<String> items = FXCollections.observableArrayList (studentArray);
        studentNamesListView.setItems(items);
    }
    */

    public void initColumns() {
        selectColumn.setCellValueFactory(new PropertyValueFactory<StudentTableView,JFXCheckBox>("Select"));
        studentsColumn.setCellValueFactory(new PropertyValueFactory<StudentTableView,String>("Student"));
    }

    ObservableList<StudentTableView> getNewStudents() {
        ObservableList<StudentTableView> students = FXCollections.observableArrayList();
        students.add(new StudentTableView("Test"));
        return students;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* TABLE VIEW BELOW */
            initColumns();
            studentTableView.setItems(getNewStudents());


        /*

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

        try {
            getStudents();
            for (int i = 0; i < (studentArray.size()); i++) {
                addStudents();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++) {
            strings.add("Item " + i);
        }

        */

    }
}