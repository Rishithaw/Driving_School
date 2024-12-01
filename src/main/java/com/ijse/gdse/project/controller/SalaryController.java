package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.SalaryDTO;
import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.dto.tm.SalaryTM;
import com.ijse.gdse.project.model.SalaryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {

    @FXML
    private AnchorPane ancPane;

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
    private ComboBox<String> cmbPayment;

    @FXML
    private ComboBox<String> cmbStaffID;

    @FXML
    private TableColumn<SalaryTM, String> colAmount;

    @FXML
    private TableColumn<SalaryTM, String> colDate;

    @FXML
    private TableColumn<SalaryTM, String> colHoliday;

    @FXML
    private TableColumn<SalaryTM, String> colID;

    @FXML
    private TableColumn<SalaryTM, String> colReceived;

    @FXML
    private TableColumn<SalaryTM, String> colStID;

    @FXML
    private DatePicker dpDop;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblStaffName;

    @FXML
    private TableView<SalaryTM> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtHoliday;

    SalaryModel salaryModel = new SalaryModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        String salaryId = lblID.getText();
        String amount = txtAmount.getText();
        String status = cmbPayment.getValue();
        Date dop = Date.valueOf(dpDop.getValue());
        String holiday = txtHoliday.getText();
        String staffId = cmbStaffID.getValue();

        SalaryDTO salaryDTO = new SalaryDTO(
                salaryId,
                amount,
                dop,
                status,
                holiday,
                staffId
        );

        boolean isSaved = salaryModel.saveSalary(salaryDTO);
        if (isSaved) {
            loadNextSalaryId();
            txtAmount.clear();
            txtHoliday.clear();
            dpDop.setValue(null);
            cmbPayment.setValue(null);
            cmbStaffID.setValue(null);
            new Alert(Alert.AlertType.INFORMATION, "Salary Saved").show();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Salary Not Saved").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String salaryId = lblID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = salaryModel.deleteSalary(salaryId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete salary").show();
            }
        }
    }

    @FXML
    void LoadIdOnAction(ActionEvent event) throws SQLException {
        String staffId =cmbStaffID.getSelectionModel().getSelectedItem();
        SalaryDTO salaryDTO = salaryModel.findById(staffId);

        if (salaryDTO != null) {
            lblStaffName.setText(salaryDTO.getStaffId());
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void settingOnAction(MouseEvent event) throws IOException {
        ancPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SettingView.fxml"));
        load.prefWidthProperty().bind(ancPane.widthProperty());
        load.prefHeightProperty().bind(ancPane.heightProperty());
        ancPane.getChildren().add(load);
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        String salaryId = lblID.getText();
        String amount = txtAmount.getText();
        String status = cmbPayment.getValue();
        Date dop = Date.valueOf(dpDop.getValue());
        String holiday = txtHoliday.getText();
        String staffId = cmbStaffID.getValue();

        SalaryDTO salaryDTO = new SalaryDTO(
                salaryId,
                amount,
                dop,
                status,
                holiday,
                staffId
        );

        boolean isSaved = salaryModel.updateSalary(salaryDTO);
        if (isSaved) {
            loadNextSalaryId();
            txtAmount.clear();
            txtHoliday.clear();
            dpDop.setValue(null);
            cmbPayment.setValue(null);
            cmbStaffID.setValue(null);
            new Alert(Alert.AlertType.INFORMATION, "Salary Updated").show();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Salary Not Updated").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<SalaryTM, String>("salaryId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<SalaryTM, String>("salary"));
        colDate.setCellValueFactory(new PropertyValueFactory<SalaryTM, String>("payDay"));
        colReceived.setCellValueFactory(new PropertyValueFactory<SalaryTM, String>("received"));
        colHoliday.setCellValueFactory(new PropertyValueFactory<SalaryTM, String>("noOfHolidays"));
        colStID.setCellValueFactory(new PropertyValueFactory<SalaryTM, String>("staffId"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load Salary id").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextSalaryId();
        loadTableData();
        loadStatus();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtAmount.clear();
        txtHoliday.clear();
        dpDop.setValue(null);
        cmbPayment.setValue(null);
        cmbStaffID.setValue(null);
    }

    private void loadTableData() throws SQLException {
        ArrayList<SalaryDTO> salaryDTOS = salaryModel.getAllSalary();
        ObservableList<SalaryTM> salaryTMS = FXCollections.observableArrayList();
        for (SalaryDTO salaryDTO : salaryDTOS) {
            SalaryTM salaryTM = new SalaryTM();
            salaryTM.setSalaryId(salaryDTO.getSalaryId());
            salaryTM.setSalary(salaryDTO.getSalary());
            salaryTM.setPayDay(salaryDTO.getPayDay());
            salaryTM.setReceived(salaryDTO.getReceived());
            salaryTM.setNoOfHolidays(salaryDTO.getNoOfHolidays());
            salaryTM.setStaffId(salaryDTO.getStaffId());
            salaryTMS.add(salaryTM);
        }
        tblSalary.setItems(salaryTMS);
    }

    private void loadNextSalaryId() throws SQLException {
        String nextSalaryId = salaryModel.getNextSalaryId();
        lblID.setText(nextSalaryId);
    }

    private void loadStatus() {
        ArrayList<String> status = salaryModel.getAllSalaryStatus();
        ObservableList<String> statuses = FXCollections.observableArrayList();
        statuses.addAll(status);
        cmbPayment.setItems(statuses);
    }
}
