package taskmanager;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.SimpleStringProperty;

public class StudentTableView {

    private JFXCheckBox select;
    private SimpleStringProperty studentName;

    public StudentTableView(String studentName) {
        this.select = new JFXCheckBox();
        this.studentName = new SimpleStringProperty(studentName);
    }

    public JFXCheckBox getSelect() {
        return select;
    }

    public String getStudentName() {
        return studentName.get();
    }


}
