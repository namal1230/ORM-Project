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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.LoginBO;
import org.example.dto.UsersDTO;
import org.example.util.PasswordUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

        String name = txtName.getText();
        boolean cname = Pattern.matches("^[A-Za-z]+$",name);
        if (!cname){
            txtName.setStyle("-fx-text-fill: RED");
            return;
        }
        String password = txtPassword.getText();
//        boolean cpassword = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",password);
//        if (!cpassword){
//            txtPassword.setStyle("-fx-text-fill: RED");
//            return;
//        }
        String value = cmbUserRole.getValue();
        if (name== null || password == null || value== null){
            new Alert(Alert.AlertType.ERROR,"Missing Fields.").show();
        }
        LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

        if (name == null && password == null) {
            new Alert(Alert.AlertType.ERROR, "USER name and Password is Empty..").show();
            return;
        }

        List<UsersDTO> usersDTOS = loginBO.checkUser(new UsersDTO(name, password, value));
        String hashpassword="0";
        for (UsersDTO usersDTO:usersDTOS){
            hashpassword=usersDTO.getPassword();
        }

        boolean isAvailable=false;

        for (UsersDTO usersDTO:usersDTOS){
            if (PasswordUtil.checkpassword(password,usersDTO.getPassword())){
                isAvailable=true;
            }
        }

        if (isAvailable) {
            if (value == "Admin") {
                Window window = txtLogin.getScene().getWindow();
                window.hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AdminDashboardForm.fxml"));
                Parent load = fxmlLoader.load();
                AdminDashboardController controller = fxmlLoader.getController();
                controller.setValue(name, hashpassword);
                Stage stage = new Stage();
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();
            } else {
                Window window = txtLogin.getScene().getWindow();
                window.hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ReceptionistDashboardForm.fxml"));
                Parent load = fxmlLoader.load();
                ReceptionistDashboardController controller = fxmlLoader.getController();
                controller.setValues(name, hashpassword);
                Stage stage = new Stage();
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}
