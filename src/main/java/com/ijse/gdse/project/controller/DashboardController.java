package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btnBookings;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnLessons;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnService;

    @FXML
    private Button btnPayments;

    @FXML
    private Button btnSalaries;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnTests;

    @FXML
    private Button btnVehicles;

    @FXML
    private ImageView imgDashBoard;

    @FXML
    private ImageView imgDriver;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgAccident;

    @FXML
    private ImageView imgService;

    @FXML
    private ImageView imgTire;

    @FXML
    private Circle cir;

    @FXML
    private Rectangle rec1;

    @FXML
    private Rectangle rec2;

    @FXML
    private Rectangle rec3;

    @FXML
    void LogoutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){
            navigateTo("/view/LoginPageView.fxml");
        }
    }

    @FXML
    void SettingOnAction(ActionEvent event) {
        navigateTo("/view/SettingView.fxml");
    }

    @FXML
    void bookingOnAction(ActionEvent event) {
        navigateTo("/view/BookingView.fxml");
    }

    @FXML
    void dashBoardOnAction(ActionEvent event) {
        navigateTo("/view/DashBoardView.fxml");
    }

    @FXML
    void lessonOnAction(ActionEvent event) {
        navigateTo("/view/LessonView.fxml");
    }

    @FXML
    void serviceOnAction(ActionEvent event) {
        navigateTo("/view/ServiceView.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("/view/PaymentView.fxml");
    }

    @FXML
    void salaryOnAction(ActionEvent event) {
        navigateTo("/view/SalaryView.fxml");
    }

    @FXML
    void studentOnAction(ActionEvent event) {
        navigateTo("/view/StudentView.fxml");
    }

    @FXML
    void testOnAction(ActionEvent event) {
        navigateTo("/view/TestView.fxml");
    }

    @FXML
    void vehicleOnAction(ActionEvent event) {
        navigateTo("/view/VehicleView.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            ancPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            //  -------- Loaded anchor edges are bound to the content anchor --------
//      (1) Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(ancPane.widthProperty());
            load.prefHeightProperty().bind(ancPane.heightProperty());

//      (2) Bind the loaded FXML to all edges of the AnchorPane
//            AnchorPane.setTopAnchor(load, 0.0);
//            AnchorPane.setRightAnchor(load, 0.0);
//            AnchorPane.setBottomAnchor(load, 0.0);
//            AnchorPane.setLeftAnchor(load, 0.0);
            ancPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        rec1.setFill(new ImagePattern(imgAccident.getImage()));
        rec2.setFill(new ImagePattern(imgTire.getImage()));
        rec3.setFill(new ImagePattern(imgService.getImage()));
    }
}
