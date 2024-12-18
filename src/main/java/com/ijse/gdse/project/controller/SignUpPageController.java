package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.SignUpDTO;
import com.ijse.gdse.project.model.SignUpModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SignUpPageController {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) {
        navigateTo("/view/LoginPageView.fxml");
    }

    @FXML
    void signInOnAction(ActionEvent event) throws SQLException {
        storeData();
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

//    private void storeData() throws SQLException {
//        String namePattern = "^[A-Za-z ]+$";
//
//        String username = txtUsername.getText();
//        String password = txtPassword.getText();
//        String address = txtAddress.getText();
//        String name = txtName.getText();
//
//        txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #none;");
//
//        boolean isValidName = name.matches(namePattern);
//
//        if (!isValidName) {
//            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
//        }
//
//        if (isValidName) {
//            if (Objects.equals(txtUsername.getText(), "") && Objects.equals(txtPassword.getText(), "") && Objects.equals(txtAddress.getText(), "") && Objects.equals(txtName.getText(), "")) {
//                new Alert(Alert.AlertType.ERROR,"Please Enter All Your Details").show(); // Line 96
//            }else {
//                SignUpModel signUpModel = new SignUpModel();
//                SignUpDTO signUpDTO = new SignUpDTO(username, name, address,password);
//                boolean isSaved = signUpModel.saveSignIn(signUpDTO);
//
//                if (isSaved) {
//                    new Alert(Alert.AlertType.INFORMATION,"Details Saved").show();
//                    navigateTo("/view/LoginPageView.fxml");
//                } else {
//                    new Alert(Alert.AlertType.ERROR,"Failed To Save Your Details").show();
//                }
//            }
//        }
//    }
private void storeData() throws SQLException {
    String namePattern = "^[A-Za-z ]+$";

    String username = txtUsername.getText();
    String password = txtPassword.getText();
    String address = txtAddress.getText();
    String name = txtName.getText();

    // Check if all fields are empty
    if (Objects.equals(username, "") && Objects.equals(password, "") && Objects.equals(address, "") && Objects.equals(name, "")) {
        new Alert(Alert.AlertType.ERROR, "Please Enter All Your Details").show();
        return; // Exit early
    }

    // Validate name separately
    boolean isValidName = name.matches(namePattern);
    if (!isValidName) {
        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        return; // Exit early if name is invalid
    }

    txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #none;");

    // Proceed with saving data
    SignUpModel signUpModel = new SignUpModel();
    SignUpDTO signUpDTO = new SignUpDTO(username, name, address, password);
    boolean isSaved = signUpModel.saveSignIn(signUpDTO);

    if (isSaved) {
        new Alert(Alert.AlertType.INFORMATION, "Details Saved").show();
        navigateTo("/view/LoginPageView.fxml");
    } else {
        new Alert(Alert.AlertType.ERROR, "Failed To Save Your Details").show();
    }
}

}