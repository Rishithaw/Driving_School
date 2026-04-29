package com.ijse.gdse.project.dto.tm;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTM {
    private String paymentId;
    private String amount;
    private String studentName;
    private Date paymentDate;
    private String paymentOption;
    private String discount;
    private String discountPrice;
}
