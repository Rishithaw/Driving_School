package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.TestDTO;
import com.ijse.gdse.project.dto.tm.TestTM;
import com.ijse.gdse.project.model.TestModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<TestTM, String> colDate;

    @FXML
    private TableColumn<TestTM, String> colID;

    @FXML
    private TableColumn<TestTM, String> colInstructor;

    @FXML
    private TableColumn<TestTM, String> colStID;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private TableColumn<TestTM, String> colTime;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblID1;

    @FXML
    private TableView<TestTM> tblTest;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtStID;

    @FXML
    private TextField txtTestDate;

    @FXML
    private TextField txtTime;

    TestModel testModel = new TestModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException {
        String testId = lblID.getText();
        String date = txtTestDate.getText();
        String time = txtTime.getText();
        String studentId = txtStID.getText();
        String instructor = txtInstructor.getText();

        TestDTO testDTO = new TestDTO(
                testId,
                date,
                time,
                studentId,
                instructor
        );

        boolean isSaved = testModel.saveTest(testDTO);
        if (isSaved) {
            loadNextTestId();
            txtTestDate.setText("");
            txtTime.setText("");
            txtStID.setText("");
            txtInstructor.setText("");
            new Alert(Alert.AlertType.INFORMATION, "Successfully Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Test").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String testId = lblID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = testModel.deleteTest(testId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete test").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        TestTM testTM = tblTest.getSelectionModel().getSelectedItem();
        if (testTM != null) {
            lblID.setText(testTM.getTestId());
            txtTestDate.setText(testTM.getDate());
            txtTime.setText(testTM.getTime());
            txtStID.setText(testTM.getStudentId());
            txtInstructor.setText(testTM.getInstructorId());

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
    void settingOnAction(MouseEvent event) throws IOException {
        ancPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SettingView.fxml"));
        load.prefWidthProperty().bind(ancPane.widthProperty());
        load.prefHeightProperty().bind(ancPane.heightProperty());
        ancPane.getChildren().add(load);
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        String testId = lblID.getText();
        String date = txtTestDate.getText();
        String time = txtTime.getText();
        String studentId = txtStID.getText();
        String instructor = txtInstructor.getText();

        TestDTO testDTO = new TestDTO(
                testId,
                date,
                time,
                studentId,
                instructor
        );

        boolean isUpdated = testModel.updateTest(testDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Successfully Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update Test").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<TestTM, String>("testId"));
        colStID.setCellValueFactory(new PropertyValueFactory<TestTM, String>("studentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<TestTM, String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<TestTM, String>("time"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<TestTM, String>("instructorId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Test id").show();
        }
    }

    private void refreshPage() throws SQLException {
        loadNextTestId();
        loadTableData();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtTestDate.setText("");
        txtTime.setText("");
        txtStID.setText("");
        txtInstructor.setText("");
    }

    private void loadNextTestId() throws SQLException {
        String nextTestId = testModel.getNextTestId();
        lblID.setText(nextTestId);
    }

    private void loadTableData() throws SQLException {
        ArrayList<TestDTO> testDTOS = testModel.getAllTest();
        ObservableList<TestTM> testTMS = FXCollections.observableArrayList();
        for (TestDTO testDTO : testDTOS) {
            TestTM testTM = new TestTM();
            testTM.setTestId(testDTO.getTestId());
            testTM.setDate(testDTO.getDate());
            testTM.setTime(testDTO.getTime());
            testTM.setInstructorId(testDTO.getInstructorId());
            testTM.setStudentId(testDTO.getStudentId());
            testTMS.add(testTM);
        }
        tblTest.setItems(testTMS);
    }
}