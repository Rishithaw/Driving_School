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
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException, SQLException {
        checkCredentials();
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
    }
}
