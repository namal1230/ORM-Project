package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.ChangeCredentialsBO;
import org.example.dto.UsersDTO;
import org.example.util.PasswordUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeCredentialsController implements Initializable {

    public JFXTextField txtName;
    public JFXPasswordField txtPassword;
    public JFXComboBox cmbJobRole;
    ChangeCredentialsBO changeCredentialsBO = (ChangeCredentialsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHANGE_CREDENTIALS);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Object> objects = FXCollections.observableArrayList();
        objects.add("Admin");
        objects.add("Receptionist");
        cmbJobRole.setItems(objects);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String password = txtPassword.getText();
        String hashPassword = PasswordUtil.hashPassword(password);
        String role = cmbJobRole.getValue().toString();

        try {
            boolean isSave = changeCredentialsBO.saveCredentials(new UsersDTO(name, hashPassword, role));
            if (isSave){
                new Alert(Alert.AlertType.INFORMATION,"User Credentials Saved Successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"User Credentials Not Saved.").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
