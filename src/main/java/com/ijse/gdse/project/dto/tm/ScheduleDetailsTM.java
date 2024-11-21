package com.ijse.gdse.project.dto.tm;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleDetailsTM {
    private String lessonId;
    private String studentId;
    private Date lessonDate;
}
