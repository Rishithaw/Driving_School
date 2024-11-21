package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.ScheduleDetailsDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDetailsModel {
    public boolean saveScheduleDetailsList(ArrayList<ScheduleDetailsDTO> scheduleDetailsDTOS) throws SQLException {
        for (ScheduleDetailsDTO scheduleDetailsDTO : scheduleDetailsDTOS) {
            boolean isScheduleDetailsSaved = saveScheduleDetails(scheduleDetailsDTO);
            if (!isScheduleDetailsSaved) {
                return false;
            }
        }
        return true;
    }

    private boolean saveScheduleDetails(ScheduleDetailsDTO scheduleDetailsDTO) throws SQLException {
        return CrudUtil.execute("insert into schedule_details values(?,?,?)",
                scheduleDetailsDTO.getLessonDate(),
                scheduleDetailsDTO.getStudentId(),
                scheduleDetailsDTO.getLessonId()
        );
    }
}
