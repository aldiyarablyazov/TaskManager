package taskmanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Utilities {

    public static void changeScene(Object controller, Node button, String file) {
        try {
            Parent newScene = FXMLLoader.load(controller.getClass().getResource(file));
            Scene scene = button.getScene();
            scene.setRoot(newScene);
            newScene.getStylesheets().add("CustomStyle.css");

        } catch (IOException e){
            System.err.println("Error switching pages");
            e.printStackTrace();
        }
    }

}