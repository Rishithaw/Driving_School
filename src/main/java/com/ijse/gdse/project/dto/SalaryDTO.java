package com.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryDTO {
    private String salaryId;
    private String salary;
    private String payDay;
    private String received;
    private String noOfHolidays;
    private String staffId;
}
