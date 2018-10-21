package taskmanager;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class TableList {

    private SimpleStringProperty task, subject;
    private SimpleBooleanProperty completed;
    private LocalDate dueDate;

    public TableList(String task, String subject, LocalDate dueDate, Boolean completed) {
        this.task = new SimpleStringProperty(task);
        this.subject = new SimpleStringProperty(subject);
        this.dueDate = dueDate;
        this.completed = new SimpleBooleanProperty(completed);
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

    public Boolean getCompleted() {
        return completed.get();
    }

}
