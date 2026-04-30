package com.ijse.gdse.project.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceDTO {
    private String serviceId;
    private Date serviceDate;
    private String reason;
    private String cost;
    private String vehicleId;
}
