package com.ijse.gdse.project.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleTM {
    private String vehicleId;
    private String vehicleType;
    private String lessonFee;
    private String admin;
    private String instructor;
    private String mechanic;
}
