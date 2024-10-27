package com.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentTM {
    private String student_id;
    private String DOB;
    private String NIC;
    private String name;
    private String address;
    private String gender;
    private String helping_aids;
    private String advance_payment;
    private String email;
}
