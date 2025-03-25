package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.config.FactoryConfiguration;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

//        Session session = FactoryConfiguration.getInstance().openSession();
//        Transaction transaction = session.beginTransaction();
//        Users user = new Users();
//        user.setName("Bhashitha");
//        user.setPassword("UNN123");
//        user.setJobRole("Receptionist ");
//
//        session.save(user);
//        transaction.commit();
//
//        session.close();
    }
}
