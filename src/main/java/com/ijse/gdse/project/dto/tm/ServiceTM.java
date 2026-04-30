package com.ijse.gdse.project.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceTM {
    private String serviceId;
    private Date serviceDate;
    private String reason;
    private String cost;
    private String vehicleId;
}
