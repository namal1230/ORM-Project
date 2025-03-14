package org.example.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public JFXTextField txtName;
    public ComboBox<String> cmbUserRole;
    public JFXPasswordField txtPassword;
    public Button txtLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Receptionist");
        cmbUserRole.setItems(objects);
    }

    public void loginOnAction(ActionEvent event) throws IOException {

        String value = cmbUserRole.getValue();
        if (value == "Admin") {
            Window window = txtLogin.getScene().getWindow();
            window.hide();
            Parent load = FXMLLoader.load(getClass().getResource("/views/AdminDashboardForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        } else {
            Window window = txtLogin.getScene().getWindow();
            window.hide();
            Parent load = FXMLLoader.load(getClass().getResource("/views/ReceptionistDashboardForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
        }

    }
}
