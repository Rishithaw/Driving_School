package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    private Circle cir;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUser;

    @FXML
    void cancelOnAction(ActionEvent event) {

    }

    @FXML
    void changeEmail(ActionEvent event) {

    }

    @FXML
    void changeNameOnAction(ActionEvent event) {

    }

    @FXML
    void changePhotoOnAction(ActionEvent event) {

    }

    @FXML
    void changeUserOnAction(ActionEvent event) {

    }

    @FXML
    void confirmOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
    }
}
