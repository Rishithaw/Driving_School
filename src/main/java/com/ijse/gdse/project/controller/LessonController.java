package com.ijse.gdse.project.controller;

import com.ijse.gdse.project.dto.LessonDTO;
import com.ijse.gdse.project.dto.tm.LessonTM;
import com.ijse.gdse.project.model.LessonModel;
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
import java.util.ArrayList;
import java.util.Optional;
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
    private TableColumn<LessonTM ,String> colLessonName;

    @FXML
    private TableColumn<LessonTM ,String> colStId;

    @FXML
    private TableColumn<LessonTM, String> colStName;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label lblID;

    @FXML
    private Label lblStID;

    @FXML
    private TableView<LessonTM> tblLesson;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtInstructor;

    @FXML
    private TextField txtLessonName;

    @FXML
    private TextField txtStName;

    LessonModel lessonModel = new LessonModel();

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String lessonId = lblID.getText();
        String duration = txtDuration.getText();
        String instructor = txtInstructor.getText();
        String lessonName = txtLessonName.getText();
        String stName = txtStName.getText();

        LessonDTO lessonDTO = new LessonDTO(
                lessonId,
                lessonName,
                duration,
                instructor
        );
        boolean isSaved = lessonModel.saveLesson(lessonDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Lesson Saved", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save Lesson").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String lessonId = lblID.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = LessonModel.deleteLesson(lessonId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete lesson").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        LessonTM lessonTM = tblLesson.getSelectionModel().getSelectedItem();
        if (lessonTM != null) {
            lblID.setText(lessonTM.getLe_id());
            txtLessonName.setText(lessonTM.getLessonName());
            txtDuration.setText(lessonTM.getTime_period());
            txtInstructor.setText(lessonTM.getIn_id());

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
        String lessonId = lblID.getText();
        String duration = txtDuration.getText();
        String instructor = txtInstructor.getText();
        String lessonName = txtLessonName.getText();
        String stName = txtStName.getText();

        LessonDTO lessonDTO = new LessonDTO(
                lessonId,
                lessonName,
                duration,
                instructor
        );
        boolean isSaved = lessonModel.updateLesson(lessonDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Lesson Updated", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to save Lesson").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cir.setFill(new ImagePattern(imgProfile.getImage()));
        colID.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("lessonId"));
        colLessonName.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("LessonName"));
        colStId.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("StudentId"));
        colStName.setCellValueFactory(new PropertyValueFactory<LessonTM, String>("studentName"));
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
        ObservableList<LessonTM> lessonTMS = FXCollections.observableArrayList();
        for (LessonDTO lessonDTO : lessonDTOS) {
            LessonTM lessonTM = new LessonTM(
                    lessonDTO.getLe_id(),
                    lessonDTO.getIn_id(),
                    lessonDTO.getLessonName(),
                    lessonDTO.getTime_period()
            );
            lessonTMS.add(lessonTM);
        }
        tblLesson.setItems(lessonTMS);
    }

    private void loadNextLessonId() throws SQLException {
        String nextLessonId = lessonModel.getNextLessonId();
                lblID.setText(nextLessonId);
    }
}
