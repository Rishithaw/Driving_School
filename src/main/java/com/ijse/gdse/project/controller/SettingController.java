package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.SignUpDTO;
import com.ijse.gdse.project.model.SignUpModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUsername;

    @FXML
    void cancelOnAction(ActionEvent event) {
        txtName.clear();
        txtEmail.clear();
        txtUsername.clear();
    }

    @FXML
    void confirmOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillFields();
        cir.setFill(new ImagePattern(imgProfile.getImage()));

    }

    private void fillFields() {
        SignUpDTO signUpDTO = new SignUpDTO();
        txtUsername.setText(signUpDTO.getUsername());
        txtName.setText(signUpDTO.getName());
        txtEmail.setText(signUpDTO.getUsername());
    }
}
