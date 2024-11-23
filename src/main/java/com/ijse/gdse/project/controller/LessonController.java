package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.LessonDTO;
import com.ijse.gdse.project.dto.ScheduleDetailsDTO;
import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.dto.tm.ScheduleTM;
import com.ijse.gdse.project.model.LessonModel;
import com.ijse.gdse.project.model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LessonController implements Initializable {

    @FXML
    private Circle cir;

    @FXML
    private ComboBox<String> cmbStID;

    @FXML
    private TableColumn<ScheduleTM, String> colDuration;

    @FXML
    private TableColumn<ScheduleTM, String> colID;

    @FXML
    private TableColumn<ScheduleTM, String> colInstructor;

    @FXML
    private TableColumn<ScheduleTM ,String> colLessonName;

    @FXML
    private TableColumn<ScheduleTM ,String> colStId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private DatePicker dpLessonDate;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblLessonID;

    @FXML
    private Label lblStName;

    @FXML
    private TableView<ScheduleTM> tblLesson;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtLessonName;

    private final ObservableList<ScheduleTM> scheduleTMS = FXCollections.observableArrayList();
    LessonModel lessonModel = new LessonModel();
    StudentModel studentModel = new StudentModel();

    @FXML
    void AddToTableOnAction(ActionEvent event) {
        String lessonId = lblLessonID.getText();
        String lessonName = txtLessonName.getText();
        String instructor = txtInstructor.getText();
        String duration = txtDuration.getText();
        Date lessonDate = Date.valueOf(dpLessonDate.getValue());
        String studentId = cmbStID.getValue();
        Button btn = new Button("Remove");

        if (studentId == null) {
            new Alert(Alert.AlertType.ERROR,"Please select a student").show();
        }

        ScheduleTM scheduleTM = new ScheduleTM(
                lessonDate,
                studentId,
                lessonId,
                lessonName,
                duration,
                instructor,
                btn
        );
        btn.setOnAction(actionEvent -> {
            scheduleTMS.remove(scheduleTM);
            tblLesson.refresh();
        });
        scheduleTMS.add(scheduleTM);
    }

    @FXML
    void ConfirmLessonOnAction(ActionEvent event) throws SQLException {
        String lessonId = lblLessonID.getText();
        String lessonName = txtLessonName.getText();
        String instructor = txtInstructor.getText();
        String duration = txtDuration.getText();
        Date lessonDate = Date.valueOf(dpLessonDate.getValue());

        if (tblLesson.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please select a lesson").show();
        }
        if (cmbStID.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please select a student").show();
        }

        ArrayList<ScheduleDetailsDTO> scheduleDetailsDTOS = new ArrayList<>();
        for (ScheduleTM scheduleTM : scheduleTMS) {
            ScheduleDetailsDTO scheduleDetailsDTO = new ScheduleDetailsDTO(
                    lessonId,
                    scheduleTM.getStudentId(),
                    lessonDate
            );
            scheduleDetailsDTOS.add(scheduleDetailsDTO);
        }
        LessonDTO lessonDTO = new LessonDTO(
                lessonId,
                lessonName,
                duration,
                instructor,
                scheduleDetailsDTOS
        );

        boolean isSaved = lessonModel.saveLesson(lessonDTO);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION,"Lesson saved").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR,"Lesson not saved").show();
        }
    }

    @FXML
    void LoadIdOnAction(ActionEvent event) throws SQLException {
        String selectedStudentId = cmbStID.getSelectionModel().getSelectedItem();
        StudentDTO studentDTO = studentModel.findById(selectedStudentId);

        if (studentDTO != null) {
            lblStName.setText(studentDTO.getName());
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        setCellValue();
        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load Lesson id").show();
        }
    }

    private void setCellValue() {
        colID.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colLessonName.setCellValueFactory(new PropertyValueFactory<>("LessonName"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeButton"));
        tblLesson.setItems(scheduleTMS);
    }

    private void refreshPage() throws SQLException {
        lblLessonID.setText(lessonModel.getNextLessonId());
        loadNextLessonId();
        loadStudentID();

        txtInstructor.setText("");
        txtLessonName.setText("");
        txtDuration.setText("");
        cmbStID.getSelectionModel().clearSelection();
        scheduleTMS.clear();
        tblLesson.refresh();
    }

    private void loadNextLessonId() throws SQLException {
        String nextLessonId = lessonModel.getNextLessonId();
        lblLessonID.setText(nextLessonId);
    }

    private void loadStudentID () throws SQLException {
        ArrayList<String> studentId = studentModel.getAllStudentId();
        ObservableList<String> studentIds = FXCollections.observableArrayList();
        studentIds.addAll(studentId);
        cmbStID.setItems(studentIds);
    }
}
