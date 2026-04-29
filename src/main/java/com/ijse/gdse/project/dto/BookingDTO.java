package com.ijse.gdse.project.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDTO {
    private String bookingId;
    private String rescheduleReason;
    private Date bookingDate;
    private String instructorId;
}
