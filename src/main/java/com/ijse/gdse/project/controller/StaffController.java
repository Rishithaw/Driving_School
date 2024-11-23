package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.StaffDTO;
import com.ijse.gdse.project.dto.tm.StaffTM;
import com.ijse.gdse.project.model.StaffModel;
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

public class StaffController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<StaffTM, String> colID;

    @FXML
    private TableColumn<StaffTM, String> colName;

    @FXML
    private TableColumn<StaffTM, String> colPID;

    @FXML
    private TableColumn<StaffTM, String> colRole;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private TableView<StaffTM> tblStaff;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRole;

    StaffModel staffModel = new StaffModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        String namePattern = "^[A-Za-z ]+$";

        String staffId = lblID.getText();
        String name = txtName.getText();
        String role = txtRole.getText();
        String personalId = txtID.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #000000;");

        boolean isValidName = name.matches(namePattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (isValidName) {
            StaffDTO staffDTO = new StaffDTO(
                    staffId,
                    name,
                    role,
                    personalId
            );

            boolean isSaved = staffModel.saveStaff(staffDTO);
            if (isSaved) {
                loadNextStaffId();
                txtName.setText("");
                txtRole.setText("");
                txtID.setText("");
                new Alert(Alert.AlertType.INFORMATION, "Successfully Saved", ButtonType.OK).show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR,"Failed to save Student").show();
            }
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String staffId = lblID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = staffModel.deleteStaff(staffId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete staff member").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        StaffTM staffTM = tblStaff.getSelectionModel().getSelectedItem();
        if (staffTM != null) {
            lblID.setText(staffTM.getStaffId());
            txtName.setText(staffTM.getName());
            txtRole.setText(staffTM.getRole());
            txtID.setText(staffTM.getStaffId());

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
    void updateOnAction(ActionEvent event) throws SQLException {
        String namePattern = "^[A-Za-z ]+$";

        String staffId = lblID.getText();
        String name = txtName.getText();
        String role = txtRole.getText();
        String personalId = txtID.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #000000;");

        boolean isValidName = name.matches(namePattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (isValidName) {
            StaffDTO staffDTO = new StaffDTO(
                    staffId,
                    name,
                    role,
                    personalId
            );

            boolean isUpdated = staffModel.updateStaff(staffDTO);
            if (isUpdated) {
                loadNextStaffId();
                txtName.setText("");
                txtRole.setText("");
                txtID.setText("");
                new Alert(Alert.AlertType.INFORMATION, "Successfully Updated", ButtonType.OK).show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR,"Failed to Update Staff Member").show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<StaffTM, String>("staffId"));
        colName.setCellValueFactory(new PropertyValueFactory<StaffTM, String>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<StaffTM, String>("role"));
        colPID.setCellValueFactory(new PropertyValueFactory<StaffTM, String>("id"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load staff id").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextStaffId();
        loadTableData();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtRole.setText("");
        txtID.setText("");
    }

    private void loadTableData() throws SQLException {
        ArrayList<StaffDTO> staffDTOS = staffModel.getAllStaff();
        ObservableList<StaffTM> staffTMS = FXCollections.observableArrayList();
        for (StaffDTO staffDTO : staffDTOS) {
            StaffTM staffTM = new StaffTM();
            staffTM.setStaffId(staffDTO.getStaffId());
            staffTM.setName(staffDTO.getName());
            staffTM.setRole(staffDTO.getRole());
            staffTM.setId(staffDTO.getId());
            staffTMS.add(staffTM);
        }
        tblStaff.setItems(staffTMS);
    }

    private void loadNextStaffId() throws SQLException {
        String nextStaffId = staffModel.getNextStaffId();
        lblID.setText(nextStaffId);
    }
}
