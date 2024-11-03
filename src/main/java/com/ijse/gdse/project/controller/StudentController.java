package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.model.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAssist;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPay;

    @FXML
    private DatePicker dpDOB;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private TableView<?> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAssist;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPay;

    @FXML
    public TextField txtNic;

StudentModel studentModel = new StudentModel();

    @FXML
    void SaveOnAction(ActionEvent event) {
        String studentId = lblID.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = String.valueOf(dpDOB.getValue());
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String assist = txtAssist.getText();
        String email = txtEmail.getText();
        String pay = txtPay.getText();

        //boolean isSaved =studentModel.saveStudent(new StudentDTO());

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
    }
}
