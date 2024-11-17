package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.dto.tm.StudentTM;
import com.ijse.gdse.project.model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
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
    private TextField txtDob;

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
        colNic.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("nic"));
        colDOB.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("DOB"));
        colGender.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("address"));
        colAssist.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("assist"));
        colEmail.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("email"));
        colPay.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("advance payment"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load customer id").show();
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        String studentId = lblID.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = txtDob.getText();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String assist = txtAssist.getText();
        String email = txtEmail.getText();
        String pay = txtPay.getText();

        StudentDTO studentDTO = new StudentDTO(
                studentId,
                name,
                nic,
                dob,
                gender,
                address,
                assist,
                email,
                pay
        );

        boolean isSaved =studentModel.saveStudent(studentDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Successfully Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save Student").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String studentId = lblID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = studentModel.deleteStudent(studentId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete student").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        StudentTM studentTM = tblStudent.getSelectionModel().getSelectedItem();
        if (studentTM != null) {
            lblID.setText(studentTM.getStudent_id());
            txtName.setText(studentTM.getName());
            txtNic.setText(studentTM.getNIC());
            txtDob.setText(studentTM.getDOB());
            txtGender.setText(studentTM.getGender());
            txtAddress.setText(studentTM.getAddress());
            txtAssist.setText(studentTM.getAssists());
            txtEmail.setText(studentTM.getEmail());
            txtPay.setText(studentTM.getAdvance_payment());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        String studentId = lblID.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = txtDob.getText();
        String gender = txtGender.getText();
        String address = txtAddress.getText();
        String assist = txtAssist.getText();
        String email = txtEmail.getText();
        String pay = txtPay.getText();

        StudentDTO studentDTO = new StudentDTO(
                studentId,
                name,
                nic,
                dob,
                gender,
                address,
                assist,
                email,
                pay
        );

        boolean isUpdated = studentModel.updateStudent(studentDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Successfully updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Fail to update student").show();

        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextStudentId();
        loadTableData();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtNic.setText("");
        txtDob.setText("");
        txtGender.setText("");
        txtAddress.setText("");
        txtAssist.setText("");
        txtEmail.setText("");
        txtPay.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS =studentModel.getAllStudent();
        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();
        for (StudentDTO studentDTO : studentDTOS) {
            StudentTM studentTM = new StudentTM(
                    studentDTO.getStudent_id(),
                    studentDTO.getName(),
                    studentDTO.getNIC(),
                    studentDTO.getDOB(),
                    studentDTO.getGender(),
                    studentDTO.getAddress(),
                    studentDTO.getAssists(),
                    studentDTO.getEmail(),
                    studentDTO.getAdvance_payment()
            );
            studentTMS.add(studentTM);
        }
        tblStudent.setItems(studentTMS);
    }

    private void loadNextStudentId() throws SQLException {
        String nextCustomerId = studentModel.getNextStudentId();
        lblID.setText(nextCustomerId);
    }
}
