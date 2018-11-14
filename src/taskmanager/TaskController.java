package taskmanager;

import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;

public class TaskController {

    private ArrayList taskIDs = new ArrayList();
    private ArrayList studentIDs = new ArrayList();
    private ArrayList taskTitles = new ArrayList();
    private ArrayList subjects = new ArrayList();
    private ArrayList descriptions = new ArrayList();
    private ArrayList teachers = new ArrayList();
    private ArrayList dueDates = new ArrayList();
    private ArrayList isCompleteds = new ArrayList();

    @FXML
    public void taskInit() throws SQLException {

        Connection dbConn = DriverManager.getConnection("jdbc:mysql://remote-mysql3.servage.net:3306/alexa", "alexa", "Alex2018");
        Statement statement = dbConn.createStatement();
        ResultSet dbResults = statement.executeQuery("SELECT * FROM alexa.Tasks ");
        ResultSetMetaData dbMetadata = dbResults.getMetaData();
        int columns = dbMetadata.getColumnCount();

        while (dbResults.next()) {
            for (int i = 1; i <= columns; i++) {
                Object value = dbResults.getObject(i);
                switch (i) {
                    case 1:
                        taskIDs.add(value);
                        break;
                    case 2:
                        studentIDs.add(value);
                        break;
                    case 3:
                        taskTitles.add(value);
                        break;
                    case 4:
                        subjects.add(value);
                        break;
                    case 5:
                        descriptions.add(value);
                        break;
                    case 6:
                        teachers.add(value);
                        break;
                    case 7:
                        dueDates.add(value);
                        break;
                    case 8:
                        isCompleteds.add(value);
                        break;
                }
            }
        }
        dbConn.close();

    }

    public ArrayList getTaskIDs() {
        return taskIDs;
    }

    public ArrayList getStudentIDs() {
        return studentIDs;
    }

    public ArrayList getTaskTitles() {
        return taskTitles;
    }

    public ArrayList getSubjects() {
        return subjects;
    }

    public ArrayList getDescriptions() {
        return descriptions;
    }

    public ArrayList getTeachers() {
        return teachers;
    }

    public ArrayList getDueDates() {
        return dueDates;
    }

    public ArrayList getIsCompleteds() {
        return isCompleteds;
    }
}
