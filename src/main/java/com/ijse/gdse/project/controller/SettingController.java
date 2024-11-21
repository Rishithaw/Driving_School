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
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUser;

    @FXML
    void cancelOnAction(ActionEvent event) {
        txtID.clear();
        txtName.clear();
        txtEmail.clear();
        txtUser.clear();
    }

    @FXML
    void changeEmail(ActionEvent event) {
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        String email = txtEmail.getText();

        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #000000;");

        boolean isValidEmail = email.matches(emailPattern);
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }
    }

    @FXML
    void changeNameOnAction(ActionEvent event) {
        String namePattern = "^[A-Za-z ]+$";

        String name = txtName.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #000000;");

        boolean isValidName = name.matches(namePattern);
        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }
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
