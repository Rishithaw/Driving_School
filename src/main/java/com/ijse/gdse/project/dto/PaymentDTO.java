package com.ijse.gdse.project.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {
    private String paymentId;
    private String amount;
    private String studentName;
    private Date paymentDate;
    private String paymentOption;
    private String discount;
    private String discountPrice;
}
