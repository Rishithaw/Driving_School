package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.BookingDTO;
import com.ijse.gdse.project.dto.tm.BookingTM;
import com.ijse.gdse.project.model.BookingModel;
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

public class BookingController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<BookingTM, Date> colDate;

    @FXML
    private TableColumn<BookingTM, String> colID;

    @FXML
    private TableColumn<BookingTM, String> colReason;

    @FXML
    private TableColumn<?, ?> colStID;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private TableColumn<BookingTM, String> colTime;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblStID;

    @FXML
    private TableView<BookingTM> tblBooking;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtReason;

    @FXML
    private TextField txtStName;

    BookingModel bookingModel = new BookingModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        BookingDTO bookingDTO = collectBookingData();

        boolean isSaved = bookingModel.saveBooking(bookingDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Booking Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Booking Not Saved").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String bookingId = lblID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = bookingModel.deleteBooking(bookingId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete booking").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        BookingTM bookingTM = tblBooking.getSelectionModel().getSelectedItem();
        if (bookingTM != null) {
            lblID.setText(bookingTM.getBookingId());
            txtName.setText(bookingTM.getInstructorId());
            txtDuration.setText(bookingTM.getBookingDate().toString());
            txtReason.setText(bookingTM.getRescheduleReason());

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
        BookingDTO bookingDTO = collectBookingData();

        boolean isUpdated = bookingModel.updateBooking(bookingDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Booking Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Booking Not Updated").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<BookingTM, String>("bookingId"));
        colTime.setCellValueFactory(new PropertyValueFactory<BookingTM, String>("instructorId"));
        colDate.setCellValueFactory(new PropertyValueFactory<BookingTM, Date>("bookingDate"));
        colReason.setCellValueFactory(new PropertyValueFactory<BookingTM, String>("rescheduleReason"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load booking data").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextBookingId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.clear();
        txtDuration.clear();
        txtReason.clear();
        txtStName.clear();
    }

    private BookingDTO collectBookingData() {
        return new BookingDTO(
                lblID.getText(),
                txtReason.getText(),
                parseDate(txtDuration.getText()),
                txtName.getText()
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

    private void loadNextBookingId() throws SQLException {
        String nextBookingId = bookingModel.getNextBookingId();
        lblID.setText(nextBookingId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<BookingDTO> bookingDTOS = bookingModel.getAllBooking();
        ObservableList<BookingTM> bookingTMS = FXCollections.observableArrayList();
        for (BookingDTO bookingDTO : bookingDTOS) {
            BookingTM bookingTM = new BookingTM();
            bookingTM.setBookingId(bookingDTO.getBookingId());
            bookingTM.setRescheduleReason(bookingDTO.getRescheduleReason());
            bookingTM.setBookingDate(bookingDTO.getBookingDate());
            bookingTM.setInstructorId(bookingDTO.getInstructorId());
            bookingTMS.add(bookingTM);
        }
        tblBooking.setItems(bookingTMS);
    }
}
