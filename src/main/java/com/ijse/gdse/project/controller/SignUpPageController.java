package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SignUpPageController {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignIn;

    @FXML
    private ImageView imgAddress;

    @FXML
    private ImageView imgDriver;

    @FXML
    private ImageView imgName;

    @FXML
    private ImageView imgPassword;

    @FXML
    private ImageView imgUsername;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        storeData();
        navigateTo("/view/LoginPageView.fxml");
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/LoginPageView.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            ancPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(ancPane.widthProperty());
            load.prefHeightProperty().bind(ancPane.heightProperty());
            ancPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load").show();
        }
    }

    private void storeData() {
    }
}