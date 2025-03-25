package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.TherapistManagementBO;
import org.example.dto.TherapistsDTO;
import org.example.tm.TherapistTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistManagementController implements Initializable {
    public JFXTextField txtName;
    public JFXTextField txtStatus;
    public TableView<TherapistTm> tblTherapist;
    public TableColumn<TherapistTm,String> colName;
    public TableColumn<TherapistTm,String> colStatus;
    public TableColumn<TherapistTm,String> colProgram;
    public JFXComboBox<String> cmbProgram;
    public JFXButton btnSave;
    public Label lblId;

    TherapistManagementBO therapistManagementBO = (TherapistManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.add("Mentally");
        cmbProgram.setItems(objects);

        loadTables();
        setId();
    }

    private void setId() {
        try {
            String lastId = therapistManagementBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTables() {
        try {
            List<TherapistsDTO> allTherapist = therapistManagementBO.getAllTherapist();
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
            ObservableList<TherapistTm> objects = FXCollections.observableArrayList();

            for (TherapistsDTO therapistsDTO:allTherapist){
                objects.add(new TherapistTm(therapistsDTO.getName(),therapistsDTO.getStatus(),therapistsDTO.getProgram()));
            }

            tblTherapist.setItems(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {

        if (btnSave.getText().equals("Save")){
            try {
                String name = txtName.getText();
                String status = txtStatus.getText();
                Object program = cmbProgram.getValue();

                boolean isSave = therapistManagementBO.saveTherapist(new TherapistsDTO(name, status, program.toString()));
                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapist Save Successfully.").show();
                    refreshPage();
                    loadTables();
                    setId();
                } else {
                    new Alert(Alert.AlertType.WARNING,"Therapist not Save.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String name = txtName.getText();
                String status = txtStatus.getText();
                String program = cmbProgram.getValue().toString();
                String id = lblId.getText();
                String lastId = id.replaceAll("^T", "");

                boolean isUpdate = therapistManagementBO.updateTherapist(new TherapistsDTO(Integer.parseInt(lastId),name, status, program));

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapist Update SuccessFully.").show();
                    refreshPage();
                    loadTables();
                    setId();
                } else {
                    new Alert(Alert.AlertType.WARNING,"Therapist Not Updated.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void refreshPage() {
        txtName.clear();
        txtStatus.clear();
        cmbProgram.setValue("");
        btnSave.setText("Save");
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        try {
            TherapistTm selectedItem = tblTherapist.getSelectionModel().getSelectedItem();
            txtName.setText(selectedItem.getName());
            txtStatus.setText(selectedItem.getStatus());
            cmbProgram.setValue(selectedItem.getProgram());

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you Want to Delete it?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                try {

                    String text = lblId.getText();
                    String id = text.replaceAll("^T", "");
                    boolean isDelete = therapistManagementBO.deleteTherapist(id);
                    if (isDelete) {
                        new Alert(Alert.AlertType.INFORMATION, "Therapist Delete SuccessFully.").show();
                        refreshPage();
                        loadTables();
                        setId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Therapist Not Deleted.").show();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                btnSave.setText("Update");
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING,"Please Select Table Row.").show();
        }

    }

    public void refreshOnAction(ActionEvent actionEvent) {
        refreshPage();
        loadTables();
        setId();
    }
}
