package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.LessonDTO;
import com.ijse.gdse.project.dto.tm.LessonTM;
import com.ijse.gdse.project.model.LessonModel;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LessonController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Circle cir;

    @FXML
    private TableColumn<LessonTM, String> colDuration;

    @FXML
    private TableColumn<LessonTM, String> colID;

    @FXML
    private TableColumn<LessonTM, String> colInstructor;

    @FXML
    private TableColumn<LessonTM, String> colStName;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblStID;

    @FXML
    private TableView<?> tblLesson;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStName;

    LessonModel lessonModel = new LessonModel();

    @FXML
    void SaveOnAction(ActionEvent event) {

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
        colID.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("lessonId"));
        colStName.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("lessonName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("duration"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("instructor"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load customer id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextLessonId();
        loadTableData();
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadTableData() throws SQLException {
        ArrayList<LessonDTO> lessonDTOS = lessonModel.getAllLesson();
        //ArrayList<StudentDTO> studentDTOS =studentModel.getAllStudent();
        //        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();
        //        for (StudentDTO studentDTO : studentDTOS) {
        //            StudentTM studentTM = new StudentTM(
        //                    studentDTO.getStudent_id(),
        //                    studentDTO.getName(),
        //                    studentDTO.getNIC(),
        //                    studentDTO.getDOB(),
        //                    studentDTO.getGender(),
        //                    studentDTO.getAddress(),
        //                    studentDTO.getAssists(),
        //                    studentDTO.getEmail(),
        //                    studentDTO.getAdvance_payment()
        //            );
        //            studentTMS.add(studentTM);
        //        }
        //        tblStudent.setItems(studentTMS);
    }

    private void loadNextLessonId() throws SQLException {
        String nextLessonId = lessonModel.getNextLessonId();
                lblID.setText(nextLessonId);
    }
}
