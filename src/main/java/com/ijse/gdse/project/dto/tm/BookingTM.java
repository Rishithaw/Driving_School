package com.ijse.gdse.project.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingTM {
    private String bookingId;
    private String rescheduleReason;
    private Date bookingDate;
    private String instructorId;
}
