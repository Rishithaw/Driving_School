package com.ijse.gdse.project.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDTO {
    private String studentId;
    private Date DOB;
    private String NIC;
    private String name;
    private String address;
    private String gender;
    private String assists;
    private String advancePayment;
    private String email;
    private String vehicleId;
}
