package com.ijse.gdse.project.dto;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleDetailsDTO {
    private String lessonId;
    private String studentId;
    private Date lessonDate;
}
