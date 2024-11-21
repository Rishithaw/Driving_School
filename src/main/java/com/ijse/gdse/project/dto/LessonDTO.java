package com.ijse.gdse.project.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonDTO {
    private String lessonId;
    private String lessonName;
    private String timePeriod;
    private String instructorId;
    private ArrayList<ScheduleDetailsDTO> scheduleDetailsDTOS;
}
