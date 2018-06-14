package taskmanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public static void changeScene(Object controller, Node button, String file, Integer width, Integer height) {
        try {

            Stage stage = (Stage) button.getScene().getWindow();

            stage.setResizable(true);

            stage.setWidth(width);
            stage.setHeight(height);

            Parent newScene = FXMLLoader.load(controller.getClass().getResource(file));
            Scene scene = button.getScene();
            scene.setRoot(newScene);
            newScene.getStylesheets().add("CustomStyle.css");

            stage.setResizable(false);

        } catch (IOException e){
            System.err.println("Error switching pages");
            e.printStackTrace();
        }
    }

    public void changeWindowSize(Node anchorPane, Integer newWidth, Integer newHeight) {
        Stage parentStage = (Stage) anchorPane.getScene().getWindow();
        parentStage.setHeight(newHeight);
        parentStage.setWidth(newWidth);

    }

}