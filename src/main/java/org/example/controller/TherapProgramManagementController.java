package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.SuperBO;
import org.example.bo.custom.TherapyProgramManagementBO;
import org.example.dto.TherapyProgramsDTO;
import org.example.tm.TherapyProgramTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapProgramManagementController implements Initializable {

    public JFXButton btnSave;
    public JFXTextField txtName;
    public JFXTextField txtDuration;
    public JFXTextField txtCost;
    public TableView<TherapyProgramTm> tblTherapyProgram;
    public TableColumn<TherapyProgramTm,String> colName;
    public TableColumn<TherapyProgramTm,String> colDuration;
    public TableColumn<TherapyProgramTm,Double> colCost;
    public TableColumn<TherapyProgramTm,String> colDescription;
    public JFXTextField txtDescription;
    public Label lblId;

    TherapyProgramManagementBO therapyProgramManagementBO = (TherapyProgramManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadTables();
        refreshPage();
        setIds();
    }

    private void setIds() {
        try {
            String lastId = therapyProgramManagementBO.getLastId();
            lblId.setText(lastId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void refreshPage() {
        txtName.clear();
        txtDuration.clear();
        txtCost.clear();
        txtDescription.clear();
    }

    private void loadTables() {

        try {
            List<TherapyProgramsDTO> therapyPrograms = therapyProgramManagementBO.getTherapyPrograms();
            ObservableList<TherapyProgramTm> objects = FXCollections.observableArrayList();
            for (TherapyProgramsDTO therapyProgramsDTO:therapyPrograms) {
                objects.add(new TherapyProgramTm(therapyProgramsDTO.getName(),therapyProgramsDTO.getDuration(),therapyProgramsDTO.getCost(),therapyProgramsDTO.getDescription()));
            }
            tblTherapyProgram.setItems(objects);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveOnAction(ActionEvent actionEvent) {

        if (btnSave.getText().equals("Save")) {
            try {
                String name = txtName.getText();
                String duration = txtDuration.getText();
                String cost = txtCost.getText();
                String description = txtDescription.getText();

                boolean isSave = therapyProgramManagementBO.saveTherapy(new TherapyProgramsDTO(name, duration, Double.parseDouble(cost), description));

                if (isSave){
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Program Save Successfully.").show();
                    loadTables();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.WARNING,"Therapy Program Not Save.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (btnSave.getText().equals("Update")) {
            try {
                String id = lblId.getText();
                String name = txtName.getText();
                String duration = txtDuration.getText();
                String cost = txtCost.getText();
                String description = txtDescription.getText();

                String lid = id.replaceAll("^TP", "");
                boolean isUpdate = therapyProgramManagementBO.updateTherapyProgram(new TherapyProgramsDTO(Integer.parseInt(lid),name, duration, Double.parseDouble(cost), description));

                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Program Update Successfully.").show();
                    loadTables();
                    refreshPage();
                    btnSave.setText("Save");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Therapy Program Not Updated.").show();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void tblOnAction(MouseEvent mouseEvent) {
        TherapyProgramTm selectedItem = tblTherapyProgram.getSelectionModel().getSelectedItem();
        txtName.setText(selectedItem.getName());
        txtDuration.setText(selectedItem.getDuration());
        txtDescription.setText(selectedItem.getDescription());
        txtCost.setText(String.valueOf(selectedItem.getCost()));

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete it?",ButtonType.YES,ButtonType.NO).showAndWait();
        if (buttonType.get()==ButtonType.YES) {

            try {
                String text = lblId.getText();
                String id = text.replaceAll("^TP", "");
                boolean isDelete = therapyProgramManagementBO.deleteTherapyProgram(id);

                if (isDelete){
                    new Alert(Alert.AlertType.INFORMATION,"Therapy Program Delete Successfully.").show();
                    loadTables();
                    refreshPage();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Therapy Program Not Deleted.").show();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            btnSave.setText("Update");
        }


    }

    public void refreshOnAction(ActionEvent actionEvent) {
        loadTables();
        refreshPage();
        btnSave.setText("Save");
    }

}
