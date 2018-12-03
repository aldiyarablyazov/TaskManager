package taskmanager;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.time.LocalDate;

public class TableList implements EventHandler<ActionEvent> {

    MasterController masterController = new MasterController();
    private SimpleStringProperty task, subject;
    private LocalDate dueDate;
    private JFXButton completed;

    public TableList(String task, String subject, LocalDate dueDate) {
        this.task = new SimpleStringProperty(task);
        this.subject = new SimpleStringProperty(subject);
        this.dueDate = dueDate;
        this.completed = new JFXButton("Finished");
        completed.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {

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

    public JFXButton getCompleted() {
        return completed;
    }

}
