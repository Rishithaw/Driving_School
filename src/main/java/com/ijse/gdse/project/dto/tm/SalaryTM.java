package com.ijse.gdse.project.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryTM {
    private String salaryId;
    private String salary;
    private Date payDay;
    private String received;
    private String noOfHolidays;
    private String staffId;
}
