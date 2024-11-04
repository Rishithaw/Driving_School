package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.dto.tm.StudentTM;
import com.ijse.gdse.project.model.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<StudentTM, String> colAddress;

    @FXML
    private TableColumn<StudentTM, String> colAssist;

    @FXML
    private TableColumn<StudentTM, String> colDOB;

    @FXML
    private TableColumn<StudentTM, String> colEmail;

    @FXML
    private TableColumn<StudentTM, String> colGender;

    @FXML
    private TableColumn<StudentTM, String> colID;

    @FXML
    private TableColumn<StudentTM, String> colName;

    @FXML
    private TableColumn<StudentTM, String> colNic;

    @FXML
    private TableColumn<StudentTM, String> colPay;

    @FXML
    private DatePicker dpDOB;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private TableView<StudentTM> tblStudent;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("name"));
        //colNic.setCellValueFactory(new PropertyValueFactory<>());
        colDOB.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("DOB"));
        colEmail.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("email"));
    }



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


}
