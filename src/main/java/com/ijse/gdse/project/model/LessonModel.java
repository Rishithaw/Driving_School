package com.ijse.gdse.project.model;

import com.ijse.gdse.project.db.DBConnection;
import com.ijse.gdse.project.dto.LessonDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonModel {
    ScheduleDetailsModel scheduleDetailsModel = new ScheduleDetailsModel();
    public String getNextLessonId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select le_id from lesson order by le_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("L%03d", newIndex);
        }
        return "L001";
    }

    public boolean saveLesson(LessonDTO lessonDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isLessonSaved = CrudUtil.execute("insert into lesson values(?,?,?,?)",
                    lessonDTO.getLessonId(),
                    lessonDTO.getLessonName(),
                    lessonDTO.getTimePeriod(),
                    lessonDTO.getInstructorId()
            );
            if (isLessonSaved) {
                boolean isScheduleDetailsSaved = scheduleDetailsModel.saveScheduleDetailsList(lessonDTO.getScheduleDetailsDTOS());
                if (isScheduleDetailsSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
