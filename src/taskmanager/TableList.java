package taskmanager;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class TableList {

    private SimpleStringProperty task, subject;
    private LocalDate dueDate;

    public TableList(String task, String subject, LocalDate dueDate) {
        this.task = new SimpleStringProperty(task);
        this.subject = new SimpleStringProperty(subject);
        this.dueDate = dueDate;
    }

    public String getTask() {
        return task.get();
    }

    public String getSubject() {
        return subject.get();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

}
