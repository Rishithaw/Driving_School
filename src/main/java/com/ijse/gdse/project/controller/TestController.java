package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class TestController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colInstructor;

    @FXML
    private TableColumn<?, ?> colStID;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private DatePicker dpTestDate;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblID1;

    @FXML
    private TableView<?> tblTest;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtStName;

    @FXML
    private TextField txtTime;

    @FXML
    void SaveOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
