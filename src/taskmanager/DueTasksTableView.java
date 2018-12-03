package taskmanager;

import javafx.beans.property.SimpleStringProperty;

public class DueTasksTableView {

    private SimpleStringProperty subject;
    private SimpleStringProperty studentName;
    private SimpleStringProperty task;
    private int isCompleted;

    public DueTasksTableView(String subject, String studentName, String task, int isCompleted) {
        this.subject = new SimpleStringProperty(subject);
        this.studentName = new SimpleStringProperty(studentName);
        this.task = new SimpleStringProperty(task);
        this.isCompleted = isCompleted;
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public String getTask() {
        return task.get();
    }

    public SimpleStringProperty taskProperty() {
        return task;
    }

    public int getIsCompleted() {
        return isCompleted;
    }
}
