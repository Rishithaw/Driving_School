package com.ijse.gdse.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleDTO {
    private String vehicleId;
    private String vehicleType;
    private String lessonFee;
    private String admin;
    private String instructor;
    private String mechanic;
}
