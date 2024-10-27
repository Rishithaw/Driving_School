package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private AnchorPane anc;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnPassword;

    @FXML
    private ImageView imgDriver;

    @FXML
    private ImageView imgPassword;

    @FXML
    private ImageView imgUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        anc.getChildren().clear();
        //AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashboardView.fxml"));
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));
        anc.getChildren().add(load);
    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

}
