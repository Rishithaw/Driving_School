package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.db.DBConnection;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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
    private ComboBox<String> cmbGender;

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
    private TableColumn<StudentTM, String> colVehicle;

    @FXML
    private DatePicker dpDob;

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
    private TextField txtName;

    @FXML
    private TextField txtPay;

    @FXML
    public TextField txtNic;

    @FXML
    private TextField txtVehicleID;

    StudentModel studentModel = new StudentModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("NIC"));
        colDOB.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("DOB"));
        colGender.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("address"));
        colAssist.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("assists"));
        colEmail.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("email"));
        colPay.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("advancePayment"));
        colVehicle.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("vehicleId"));

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
        Date dob = Date.valueOf(dpDob.getValue());
        String gender = cmbGender.getValue();
        String address = txtAddress.getText();
        String assist = txtAssist.getText();
        String email = txtEmail.getText();
        String pay = txtPay.getText();
        String vehicle  =txtVehicleID.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #000000;");
        txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #000000;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #000000;");

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidNic) {
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidNic && isValidEmail) {
            StudentDTO studentDTO = new StudentDTO(
                    studentId,
                    dob,
                    nic,
                    name,
                    gender,
                    address,
                    assist,
                    pay,
                    email,
                    vehicle
            );

            boolean isSaved = studentModel.saveStudent(studentDTO);
            if (isSaved) {
                loadNextStudentId();
                txtName.setText("");
                txtNic.setText("");
                dpDob.setValue(null);
                cmbGender.getSelectionModel().clearSelection();
                txtAddress.setText("");
                txtAssist.setText("");
                txtEmail.setText("");
                txtPay.setText("");
                txtVehicleID.setText("");
                new Alert(Alert.AlertType.INFORMATION,"Successfully Saved").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR,"Failed to save Student").show();
            }
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
            lblID.setText(studentTM.getStudentId());
            txtName.setText(studentTM.getName());
            txtNic.setText(studentTM.getNIC());
            Date date = studentTM.getDOB();
            dpDob.setValue(date.toLocalDate());
            cmbGender.getSelectionModel().select(studentTM.getGender());
            txtAddress.setText(studentTM.getAddress());
            txtAssist.setText(studentTM.getAssists());
            txtEmail.setText(studentTM.getEmail());
            txtPay.setText(studentTM.getAdvancePayment());
            txtVehicleID.setText(studentTM.getVehicleId());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/Student_Report.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


        String studentId = lblID.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        Date dob = Date.valueOf(dpDob.getValue());
        String gender = cmbGender.getValue();
        String address = txtAddress.getText();
        String assist = txtAssist.getText();
        String email = txtEmail.getText();
        String pay = txtPay.getText();
        String vehicle = txtVehicleID.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color:  #000000;");
        txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #000000;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #000000;");

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
        }

        if (!isValidNic) {
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidNic && isValidEmail) {
            StudentDTO studentDTO = new StudentDTO(
                    studentId,
                    dob,
                    nic,
                    name,
                    address,
                    gender,
                    assist,
                    pay,
                    email,
                    vehicle
            );

            boolean isUpdated = studentModel.updateStudent(studentDTO);
            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to update student").show();

            }
        }

    }

    private void refreshPage() throws SQLException {
        loadNextStudentId();
        loadTableData();
        loadGender();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtNic.setText("");
        dpDob.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        txtAddress.setText("");
        txtAssist.setText("");
        txtEmail.setText("");
        txtPay.setText("");
        txtVehicleID.setText("");
    }

    private void loadTableData() throws SQLException {
        ArrayList<StudentDTO> studentDTOS =studentModel.getAllStudent();
        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();
        for (StudentDTO studentDTO : studentDTOS) {
            StudentTM studentTM = new StudentTM();
                    studentTM.setStudentId(studentDTO.getStudentId());
                    studentTM.setName(studentDTO.getName());
                    studentTM.setNIC(studentDTO.getNIC());
                    studentTM.setDOB(studentDTO.getDOB());
                    studentTM.setGender(studentDTO.getGender());
                    studentTM.setAddress(studentDTO.getAddress());
                    studentTM.setAssists(studentDTO.getAssists());
                    studentTM.setEmail(studentDTO.getEmail());
                    studentTM.setAdvancePayment(studentDTO.getAdvancePayment());
                    studentTM.setVehicleId(studentDTO.getVehicleId());
                    studentTMS.add(studentTM);
        }
        tblStudent.setItems(studentTMS);
    }

    private void loadNextStudentId() throws SQLException {
        String nextCustomerId = studentModel.getNextStudentId();
        lblID.setText(nextCustomerId);
    }

    private void loadGender() throws SQLException {
        ArrayList<String> gender = studentModel.getAllGender();
        ObservableList<String> genders = FXCollections.observableArrayList();
        genders.addAll(gender);
        cmbGender.setItems(genders);
    }
}
