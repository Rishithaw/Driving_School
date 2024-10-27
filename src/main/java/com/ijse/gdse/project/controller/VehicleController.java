package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class VehicleController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colInstructor;

    @FXML
    private TableColumn<?, ?> colLessonFee;

    @FXML
    private TableColumn<?, ?> colMechanic;

    @FXML
    private TableColumn<?, ?> colVehicleType;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private TableView<?> tblVehicle;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtLessonFee;

    @FXML
    private TextField txtMechanic;

    @FXML
    private TextField txtVehicle;

    @FXML
    void SaveOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
