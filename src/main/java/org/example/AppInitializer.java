package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/views/LoginForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();
    }
}
