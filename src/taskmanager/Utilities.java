package taskmanager;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Utilities {

    public static void changeScene(Object controller, Node button, String file) {
        try {
            Stage stage = (Stage) button.getScene().getWindow();

            // change scene
            Parent newScene = FXMLLoader.load(controller.getClass().getResource(file));
            Scene scene = button.getScene();
            scene.setRoot(newScene);
            newScene.getStylesheets().add("TabPaneStyle.css");

            // center stage on screen
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);


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

            // change scene
            Parent newScene = FXMLLoader.load(controller.getClass().getResource(file));
            Scene scene = button.getScene();
            scene.setRoot(newScene);
            newScene.getStylesheets().add("TabPaneStyle.css");

            // center stage on screen
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

            stage.setResizable(false);

        } catch (IOException e){
            System.err.println("Error switching pages");
            e.printStackTrace();
        }
    }

}