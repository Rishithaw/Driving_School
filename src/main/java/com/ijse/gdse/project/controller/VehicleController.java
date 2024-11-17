package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.VehicleDTO;
import com.ijse.gdse.project.dto.tm.VehicleTM;
import com.ijse.gdse.project.model.VehicleModel;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<VehicleTM, String> colAdmin;

    @FXML
    private TableColumn<VehicleTM, String> colID;

    @FXML
    private TableColumn<VehicleTM, String> colInstructor;

    @FXML
    private TableColumn<VehicleTM, String> colLessonFee;

    @FXML
    private TableColumn<VehicleTM, String> colMechanic;

    @FXML
    private TableColumn<VehicleTM, String> colVehicleType;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    private TextField txtAdmin;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtLessonFee;

    @FXML
    private TextField txtMechanic;

    @FXML
    private TextField txtVehicle;

    VehicleModel vehicleModel = new VehicleModel();

    @FXML
    void SaveOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String vehicleId = lblID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = vehicleModel.deleteVehicle(vehicleId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete student").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        VehicleTM vehicleTM = tblVehicle.getSelectionModel().getSelectedItem();
        if (vehicleTM != null) {
            lblID.setText(vehicleTM.getVec_id());
            txtVehicle.setText(vehicleTM.getVehicle_type());
            txtLessonFee.setText(vehicleTM.getLesson_fee());
            txtAdmin.setText(vehicleTM.getAd_id());
            txtInstructor.setText(vehicleTM.getIn_id());
            txtMechanic.setText(vehicleTM.getMec_id());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<VehicleTM, String>("vehicleId"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<VehicleTM, String>("vehicleType"));
        colLessonFee.setCellValueFactory(new PropertyValueFactory<VehicleTM, String>("lessonFee"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<VehicleTM, String>("admin"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<VehicleTM, String>("instructor"));
        colMechanic.setCellValueFactory(new PropertyValueFactory<VehicleTM, String>("mechanic"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load customer id").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextVehicleId();
        loadTableData();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtVehicle.setText("");
        txtLessonFee.setText("");
        txtAdmin.setText("");
        txtInstructor.setText("");
        txtMechanic.setText("");
    }

    private void loadTableData() throws SQLException {
        ArrayList<VehicleDTO> vehicleDTOS = vehicleModel.getAllVehicles();
        ObservableList<VehicleTM> vehicleTMS = FXCollections.observableArrayList();
        for (VehicleDTO vehicleDTO : vehicleDTOS) {
            VehicleTM vehicleTM = new VehicleTM();
            vehicleTM.setVec_id(vehicleDTO.getVec_id());
            vehicleTM.setVehicle_type(vehicleDTO.getVehicle_type());
            vehicleTM.setLesson_fee(vehicleDTO.getLesson_fee());
            vehicleTM.setAd_id(vehicleDTO.getAd_id());
            vehicleTM.setIn_id(vehicleDTO.getIn_id());
            vehicleTM.setMec_id(vehicleDTO.getMec_id());
        }
        tblVehicle.setItems(vehicleTMS);
    }

    private void loadNextVehicleId() throws SQLException {
        String vehicleId = vehicleModel.getNextVehicleId();
        lblID.setText(vehicleId);
    }
}
