package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.LessonDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LessonModel {
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
        boolean isSaved = CrudUtil.execute("insert into lesson values(?,?,?,?)");
            lessonDTO.getLessonId();
            lessonDTO.getInstructorId();
            lessonDTO.getLessonName();
            lessonDTO.getTimePeriod();
            return isSaved;
    }

    public ArrayList<LessonDTO> getAllLesson() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from lesson");
        ArrayList<LessonDTO> lessonDTOS = new ArrayList<>();
        while (rst.next()) {
            LessonDTO lessonDTO = new LessonDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            lessonDTOS.add(lessonDTO);
        }
        return lessonDTOS;
    }

    public boolean updateLesson(LessonDTO lessonDTO) throws SQLException {
        return CrudUtil.execute("update lesson set in_id=?, description=?, time_period=?",
                lessonDTO.getInstructorId(),
                lessonDTO.getLessonName(),
                lessonDTO.getTimePeriod()
        );
    }

    public static boolean deleteLesson(String lessonId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from lesson where lesson_id=?",lessonId);
    }

    public ArrayList<String> getAllLessonId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select lesson_id from lesson");
        ArrayList<String> studentIds = new ArrayList<>();
        while (rst.next()) {
            studentIds.add(rst.getString(1));
        }
        return studentIds;
    }

    public LessonDTO findById(String selectedLeId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from lesson where lesson_id=?",selectedLeId);

        if (rst.next()) {
            return new LessonDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }
}
