package taskmanager;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class StudentTableView {

    private SimpleStringProperty studentName;
    private JFXCheckBox select;

    public StudentTableView(String studentName) {
        this.studentName = new SimpleStringProperty(studentName);
        this.select = new JFXCheckBox();
    }

    public String getStudentName() {
        return studentName.get();
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(JFXCheckBox select) {
        this.select = select;
    }
}
