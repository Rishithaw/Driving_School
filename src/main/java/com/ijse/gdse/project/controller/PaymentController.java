package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.PaymentDTO;
import com.ijse.gdse.project.dto.tm.PaymentTM;
import com.ijse.gdse.project.model.PaymentModel;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    private static final String DEFAULT_ADMIN_ID = "A001";

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private ComboBox<String> cmbDiscount;

    @FXML
    private ComboBox<String> cmbPayment;

    @FXML
    private TableColumn<PaymentTM, String> colAmount;

    @FXML
    private TableColumn<PaymentTM, Date> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colDiscount;

    @FXML
    private TableColumn<PaymentTM, String> colDiscountPrice;

    @FXML
    private TableColumn<PaymentTM, String> colID;

    @FXML
    private TableColumn<PaymentTM, String> colPayOption;

    @FXML
    private TableColumn<PaymentTM, String> colStName;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblPayID;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDOP;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtStudentName;

    PaymentModel paymentModel = new PaymentModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        PaymentDTO paymentDTO = collectPaymentData();

        boolean isSaved = paymentModel.savePayment(paymentDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment Not Saved").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String paymentId = lblPayID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = paymentModel.deletePayment(paymentId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete payment").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTM paymentTM = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTM != null) {
            lblPayID.setText(paymentTM.getPaymentId());
            lblID.setText(paymentTM.getPlanId());
            txtAmount.setText(paymentTM.getPrice());
            txtStudentName.setText(paymentTM.getStudentId());
            txtDOP.setText(paymentTM.getPaidDate().toString());
            cmbPayment.setValue(paymentTM.getPaymentMethod());
            cmbDiscount.setValue(paymentTM.getDiscount());
            txtDiscount.setText(paymentTM.getDiscountPrice());

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
        PaymentDTO paymentDTO = collectPaymentData();

        boolean isUpdated = paymentModel.updatePayment(paymentDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment Not Updated").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<PaymentTM, String>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<PaymentTM, String>("price"));
        colStName.setCellValueFactory(new PropertyValueFactory<PaymentTM, String>("studentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<PaymentTM, Date>("paidDate"));
        colPayOption.setCellValueFactory(new PropertyValueFactory<PaymentTM, String>("paymentMethod"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<PaymentTM, String>("discount"));
        colDiscountPrice.setCellValueFactory(new PropertyValueFactory<PaymentTM, String>("discountPrice"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load payment data").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextPaymentIds();
        loadTableData();
        loadPaymentOptions();
        loadDiscountOptions();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtAmount.clear();
        txtStudentName.clear();
        txtDOP.clear();
        txtDiscount.clear();
        cmbPayment.setValue(null);
        cmbDiscount.setValue(null);
    }

    private PaymentDTO collectPaymentData() {
        String paymentId = lblPayID.getText();
        String planId = lblID.getText();
        String amount = txtAmount.getText();
        String studentId = txtStudentName.getText();
        Date paidDate = parseDate(txtDOP.getText());
        String paymentMethod = cmbPayment.getValue();
        String discount = cmbDiscount.getValue();
        String discountPrice = getDiscountPrice(amount, discount);

        return new PaymentDTO(
                paymentId,
                paymentMethod,
                paidDate,
                DEFAULT_ADMIN_ID,
                planId,
                paymentMethod,
                amount,
                discount,
                discountPrice,
                studentId
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

    private String getDiscountPrice(String amountText, String discountText) {
        if (!txtDiscount.getText().isBlank()) {
            return txtDiscount.getText();
        }

        try {
            double amount = Double.parseDouble(amountText);
            double discount = discountText == null ? 0 : Double.parseDouble(discountText);
            double discountPrice = amount - (amount * discount / 100);
            return String.format("%.2f", discountPrice);
        } catch (NumberFormatException | DateTimeParseException e) {
            return txtDiscount.getText();
        }
    }

    private void loadNextPaymentIds() throws SQLException {
        lblPayID.setText(paymentModel.getNextPaymentId());
        lblID.setText(paymentModel.getNextPaymentPlanId());
    }

    private void loadTableData() throws SQLException {
        ArrayList<PaymentDTO> paymentDTOS = paymentModel.getAllPayment();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();
        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM();
            paymentTM.setPaymentId(paymentDTO.getPaymentId());
            paymentTM.setPaymentMethod(paymentDTO.getPaymentMethod());
            paymentTM.setPaidDate(paymentDTO.getPaidDate());
            paymentTM.setAdminId(paymentDTO.getAdminId());
            paymentTM.setPlanId(paymentDTO.getPlanId());
            paymentTM.setPlanMethod(paymentDTO.getPlanMethod());
            paymentTM.setPrice(paymentDTO.getPrice());
            paymentTM.setDiscount(paymentDTO.getDiscount());
            paymentTM.setDiscountPrice(paymentDTO.getDiscountPrice());
            paymentTM.setStudentId(paymentDTO.getStudentId());
            paymentTMS.add(paymentTM);
        }
        tblPayment.setItems(paymentTMS);
    }

    private void loadPaymentOptions() {
        ObservableList<String> paymentOptions = FXCollections.observableArrayList();
        paymentOptions.addAll(paymentModel.getAllPaymentOptions());
        cmbPayment.setItems(paymentOptions);
    }

    private void loadDiscountOptions() {
        ObservableList<String> discountOptions = FXCollections.observableArrayList();
        discountOptions.addAll(paymentModel.getAllDiscountOptions());
        cmbDiscount.setItems(discountOptions);
    }

}
