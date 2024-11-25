package com.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonTM {
    private String lessonId;
    private String lessonName;
    private String timePeriod;
    private String instructorId;
}
