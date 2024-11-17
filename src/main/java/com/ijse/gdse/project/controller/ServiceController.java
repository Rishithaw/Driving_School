package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ServiceController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colReason;

    @FXML
    private DatePicker dpDOS;

    @FXML
    private Label lblID;

    @FXML
    private Label lblServiceID;

    @FXML
    private TableView<?> tblService;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtReason;

    @FXML
    private TextField txtVehicleName;

    @FXML
    void SaveOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        //refreshPage();
    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
