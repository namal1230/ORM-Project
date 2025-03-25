package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.bo.BOFactory;
import org.example.bo.custom.TherapySessionSchedulingBO;
import org.example.dto.TherapySessionsDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class  TherapySessionSchedulingController implements Initializable {
    public JFXButton btnSave;
    public JFXTextField txtCost;
    public JFXTextField txtDescription;
    public JFXComboBox<String> cmbTerapy;
    public JFXComboBox<String> cmbPatient;
    public Label lblId;
    public JFXComboBox<String> cmbProgram;
    TherapySessionSchedulingBO therapySessionSchedulingBO = (TherapySessionSchedulingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPY_SESSION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadIds();
        refreshPage();
        setValues();
    }

    private void setValues() {
        try {
            List allPatientId = therapySessionSchedulingBO.getAllPatientId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allPatientId){
                objects.add(value.toString());
            }
            cmbPatient.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allProgramId = therapySessionSchedulingBO.getAllTherapyId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allProgramId){
                objects.add(value.toString());
            }
            cmbProgram.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List allTherapyProgramId = therapySessionSchedulingBO.getAllProgramId();
            ObservableList<String> objects = FXCollections.observableArrayList();
            for (Object value:allTherapyProgramId){
                objects.add(value.toString());
            }
            cmbTerapy.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() {
        txtCost.clear();
        txtDescription.clear();
        cmbPatient.setValue("");
        cmbTerapy.setValue("");
        cmbProgram.setValue("");
    }

    private void loadIds() {
        try {
            String lastId = therapySessionSchedulingBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveOnAction(ActionEvent actionEvent) {
        String patient = cmbPatient.getValue();
        String therapy = cmbTerapy.getValue();
        String program = cmbProgram.getValue();
        String cost = txtCost.getText();
        String description = txtDescription.getText();

        if (btnSave.getText().equals("Save")){
            try {
                boolean isSave = therapySessionSchedulingBO.saveTherapySession(new TherapySessionsDTO(Integer.parseInt(therapy), Integer.parseInt(patient), Integer.parseInt(program), Double.parseDouble(cost), description));

                if (isSave){
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Session Save Successfully.").show();
                    refreshPage();
                    loadIds();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Therapy Session Not Save.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String id = lblId.getText();
                String lid = id.replaceAll("^TS", "");
                boolean isUpdate = therapySessionSchedulingBO.updateTherapySession(new TherapySessionsDTO(Integer.parseInt(lid),Integer.parseInt(therapy), Integer.parseInt(patient), Integer.parseInt(program), Double.parseDouble(cost), description));

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Session Update Successfully.").show();
                    refreshPage();
                    loadIds();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Therapy Session Not Updated.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        refreshPage();
        loadIds();
    }

}
