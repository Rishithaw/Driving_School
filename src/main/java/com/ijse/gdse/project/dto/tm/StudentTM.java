package com.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentTM {
    private String studentId;
    private String DOB;
    private String NIC;
    private String name;
    private String address;
    private String gender;
    private String assists;
    private String advancePayment;
    private String email;
    private String vehicleId;
}
