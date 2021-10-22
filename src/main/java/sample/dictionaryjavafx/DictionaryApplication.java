package sample.dictionaryjavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("TDQ Dictionary");
        Scene sceneUser = new Scene(root);
        stage.setScene(sceneUser);
        stage.setResizable(false);
        stage.show();
    }

    public static void runApplication() {
        launch();
    }

    public static void main(String[] args) {
        runApplication();
    }
}