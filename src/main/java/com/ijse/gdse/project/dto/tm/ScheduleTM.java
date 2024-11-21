package com.ijse.gdse.project.dto.tm;

import java.sql.Date;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleTM {
    private Date lessonDate;
    private String studentId;
    private String lessonId;
    private String lessonName;
    private String timePeriod;
    private String instructorId;
    private Button removeButton;
}
