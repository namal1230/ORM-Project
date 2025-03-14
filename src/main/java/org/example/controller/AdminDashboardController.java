package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashboardController {
    public AnchorPane dashAnc;

    public void patientHistoryOnAction(ActionEvent event) throws IOException {
        dashAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PatientTheropyHistoryForm.fxml"));
        dashAnc.getChildren().add(load);
    }

    public void theropyProgramOnAction(ActionEvent event) throws IOException {
        dashAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/TherapyProgramManagementForm.fxml"));
        dashAnc.getChildren().add(load);
    }

    public void therapistOnAction(ActionEvent event) throws IOException {
        dashAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/TherapistManagementForm.fxml"));
        dashAnc.getChildren().add(load);
    }

}
