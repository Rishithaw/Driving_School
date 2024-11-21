package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.SignUpDTO;
import com.ijse.gdse.project.model.LoginModel;
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
import java.util.ArrayList;

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
    void loginOnAction(ActionEvent event) throws IOException, SQLException {
        checkCredentials();

    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

    private void checkCredentials() throws SQLException, IOException {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        if (txtUsername.getText() == "" && txtPassword.getText() == "") {
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Login Details").show();
        } else {
            LoginModel loginModel = new LoginModel();
            ArrayList<SignUpDTO> signUpDTOS =loginModel.getCredentials();

            for (SignUpDTO signUpDTO : signUpDTOS) {
                if (signUpDTO.getUsername().equals(userName) && signUpDTO.getPassword().equals(password)) {
                    System.out.println("Login Success");
                    anc.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));
                    anc.getChildren().add(load);
                }
            }
        }

//if (txtUsername.getText() == "" && txtPassword.getText() == "" && txtAddress.getText() == "" && txtName.getText() == "") {
//            new Alert(Alert.AlertType.ERROR,"Please Enter All Your Details").show();
//        }else {
//            SignUpModel signUpModel = new SignUpModel();
//
//            SignUpDTO signUpDTO = new SignUpDTO(username, name, address,password);
//
//            boolean isSaved = signUpModel.saveSignIn(signUpDTO);
//
//            if (isSaved) {
//                new Alert(Alert.AlertType.INFORMATION,"Details Saved").show();
//                navigateTo("/view/LoginPageView.fxml");
//            } else {
//                new Alert(Alert.AlertType.ERROR,"Failed To Save Your Details").show();
//            }
//        }


    }
}
