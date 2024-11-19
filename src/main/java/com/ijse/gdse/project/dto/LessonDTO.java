package com.ijse.gdse.project.dto;

import lombok.*;

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
}
