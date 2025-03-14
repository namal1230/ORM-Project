package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReceptionistDashboardController {
    public AnchorPane ReceptionistAnc;

    public void patientOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PatientManagementForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void therapySessionOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/TherapySessionSchedulingForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void paymentOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PaymentInvoiceManagementForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }

    public void patientHistoryOnAction(ActionEvent event) throws IOException {
        ReceptionistAnc.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/views/PatientTheropyHistoryForm.fxml"));
        ReceptionistAnc.getChildren().add(load);
    }
}
