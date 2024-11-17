package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private ImageView imgDriver;

    @FXML
    void LogoutImgOnAction(MouseEvent event) {
        handleLogout(event);
    }

    @FXML
    void LogoutOnAction(ActionEvent event) {
        handleLogout(event);
    }

    @FXML
    void SettingImgOnAction(MouseEvent event) {
        navigateTo("/view/SettingView.fxml");
    }

    @FXML
    void SettingOnAction(ActionEvent event) {
        navigateTo("/view/SettingView.fxml");
    }

    @FXML
    void StaffImgOnAction(MouseEvent event) {
        navigateTo("/view/StaffView.fxml");
    }

    @FXML
    void StaffOnAction(ActionEvent event) {
        navigateTo("/view/StaffView.fxml");
    }

    @FXML
    void bookingImgOnAction(MouseEvent event) {
        navigateTo("/view/BookingView.fxml");
    }

    @FXML
    void bookingOnAction(ActionEvent event) {
        navigateTo("/view/BookingView.fxml");
    }

    @FXML
    public void dashBoardImgOnAction(MouseEvent mouseEvent) {
        navigateTo("/view/DashBoardView.fxml");
    }

    @FXML
    void dashBoardOnAction(ActionEvent event) {
        navigateTo("/view/DashBoardView.fxml");
    }

    @FXML
    void lessonImgOnAction(MouseEvent event) {
        navigateTo("/view/LessonView.fxml");
    }

    @FXML
    void lessonOnAction(ActionEvent event) {
        navigateTo("/view/LessonView.fxml");
    }

    @FXML
    void serviceImgOnAction(MouseEvent event) {
        navigateTo("/view/ServiceView.fxml");
    }

    @FXML
    void serviceOnAction(ActionEvent event) {
        navigateTo("/view/ServiceView.fxml");
    }

    @FXML
    void paymentImgOnAction(MouseEvent event) {
        navigateTo("/view/PaymentView.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("/view/PaymentView.fxml");
    }

    @FXML
    void salaryImgOnAction(MouseEvent event) {
        navigateTo("/view/SalaryView.fxml");
    }

    @FXML
    void salaryOnAction(ActionEvent event) {
        navigateTo("/view/SalaryView.fxml");
    }

    @FXML
    void studentImgOnAction(MouseEvent event) {
        navigateTo("/view/StudentView.fxml");
    }

    @FXML
    void studentOnAction(ActionEvent event) {
        navigateTo("/view/StudentView.fxml");
    }

    @FXML
    void testImgOnAction(MouseEvent event) {
        navigateTo("/view/TestView.fxml");
    }

    @FXML
    void testOnAction(ActionEvent event) {
        navigateTo("/view/TestView.fxml");
    }

    @FXML
    void vehicleImgOnAction(MouseEvent event) {
        navigateTo("/view/VehicleView.fxml");
    }

    @FXML
    void vehicleOnAction(ActionEvent event) {
        navigateTo("/view/VehicleView.fxml");
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

    private void handleLogout(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            navigateTo("/view/LoginPageView.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/StudentView.fxml");
    }
}
