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
    private String paymentMethod;
    private Date paidDate;
    private String adminId;
    private String planId;
    private String planMethod;
    private String price;
    private String discount;
    private String discountPrice;
    private String studentId;
}
