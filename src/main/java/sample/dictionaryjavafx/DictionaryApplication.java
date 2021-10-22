package sample.dictionaryjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("TDQ Dictionary");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void runApplication() {
        launch();
    }

    public static void main(String[] args) {
        runApplication();
    }
}