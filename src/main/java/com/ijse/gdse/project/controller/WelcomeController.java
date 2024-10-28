package com.ijse.gdse.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private Circle cir;

    @FXML
    private ImageView imgAccident;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgService;

    @FXML
    private ImageView imgTire;

    @FXML
    private Rectangle rec1;

    @FXML
    private Rectangle rec2;

    @FXML
    private Rectangle rec3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        rec1.setFill(new ImagePattern(imgAccident.getImage()));
        rec2.setFill(new ImagePattern(imgService.getImage()));
        rec3.setFill(new ImagePattern(imgTire.getImage()));
    }
}
