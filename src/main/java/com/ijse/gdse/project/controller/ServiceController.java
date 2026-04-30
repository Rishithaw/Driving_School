package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.ServiceDTO;
import com.ijse.gdse.project.dto.tm.ServiceTM;
import com.ijse.gdse.project.model.ServiceModel;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<ServiceTM, String> colCost;

    @FXML
    private TableColumn<ServiceTM, Date> colDate;

    @FXML
    private TableColumn<ServiceTM, String> colID;

    @FXML
    private TableColumn<ServiceTM, String> colReason;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblServiceID;

    @FXML
    private TableView<ServiceTM> tblService;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDOS;

    @FXML
    private TextField txtReason;

    @FXML
    private TextField txtVehicleName;

    ServiceModel serviceModel = new ServiceModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        ServiceDTO serviceDTO = collectServiceData();

        boolean isSaved = serviceModel.saveService(serviceDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Service Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Service Not Saved").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String serviceId = lblServiceID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = serviceModel.deleteService(serviceId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete service").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ServiceTM serviceTM = tblService.getSelectionModel().getSelectedItem();
        if (serviceTM != null) {
            lblServiceID.setText(serviceTM.getServiceId());
            txtDOS.setText(serviceTM.getServiceDate().toString());
            txtReason.setText(serviceTM.getReason());
            txtCost.setText(serviceTM.getCost());
            txtVehicleName.setText(serviceTM.getVehicleId());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        ServiceDTO serviceDTO = collectServiceData();

        boolean isUpdated = serviceModel.updateService(serviceDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Service Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Service Not Updated").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<ServiceTM, String>("serviceId"));
        colDate.setCellValueFactory(new PropertyValueFactory<ServiceTM, Date>("serviceDate"));
        colReason.setCellValueFactory(new PropertyValueFactory<ServiceTM, String>("reason"));
        colCost.setCellValueFactory(new PropertyValueFactory<ServiceTM, String>("cost"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load service data").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextServiceId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtVehicleName.clear();
        txtDOS.clear();
        txtReason.clear();
        txtCost.clear();
    }

    private ServiceDTO collectServiceData() {
        return new ServiceDTO(
                lblServiceID.getText(),
                parseDate(txtDOS.getText()),
                txtReason.getText(),
                txtCost.getText(),
                txtVehicleName.getText()
        );
    }

    private Date parseDate(String dateText) {
        try {
            return Date.valueOf(dateText);
        } catch (IllegalArgumentException ignored) {
            LocalDate localDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            return Date.valueOf(localDate);
        }
    }

    private void loadNextServiceId() throws SQLException {
        String nextServiceId = serviceModel.getNextServiceId();
        lblServiceID.setText(nextServiceId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<ServiceDTO> serviceDTOS = serviceModel.getAllService();
        ObservableList<ServiceTM> serviceTMS = FXCollections.observableArrayList();
        for (ServiceDTO serviceDTO : serviceDTOS) {
            ServiceTM serviceTM = new ServiceTM();
            serviceTM.setServiceId(serviceDTO.getServiceId());
            serviceTM.setServiceDate(serviceDTO.getServiceDate());
            serviceTM.setReason(serviceDTO.getReason());
            serviceTM.setCost(serviceDTO.getCost());
            serviceTM.setVehicleId(serviceDTO.getVehicleId());
            serviceTMS.add(serviceTM);
        }
        tblService.setItems(serviceTMS);
    }
}
