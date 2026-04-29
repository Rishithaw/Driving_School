package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.PaymentDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {
    public String getNextPaymentId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select payment_id from payment order by payment_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i + 1;
            return String.format("P%03d", newIndex);
        }
        return "P001";
    }

    public String getNextPaymentPlanId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select plan_id from payment_plan order by plan_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIndex = i + 1;
            return String.format("Pl%03d", newIndex);
        }
        return "Pl001";
    }

    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException {
        boolean isPaymentSaved = CrudUtil.execute("insert into payment values(?,?,?,?)",
                paymentDTO.getPaymentId(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getPaidDate(),
                paymentDTO.getAdminId()
        );

        if (!isPaymentSaved) {
            return false;
        }

        return CrudUtil.execute("insert into payment_plan values(?,?,?,?,?,?,?)",
                paymentDTO.getPlanId(),
                paymentDTO.getPlanMethod(),
                paymentDTO.getPrice(),
                paymentDTO.getDiscount(),
                paymentDTO.getDiscountPrice(),
                paymentDTO.getStudentId(),
                paymentDTO.getPaymentId()
        );
    }

    public ArrayList<PaymentDTO> getAllPayment() throws SQLException {
        ResultSet rst = CrudUtil.execute(
                "select p.payment_id, p.method, p.date_paid, p.ad_id, pp.plan_id, pp.method, pp.price, pp.discount, pp.discounted_price, pp.st_id " +
                        "from payment p join payment_plan pp on p.payment_id = pp.pay_id"
        );
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        while (rst.next()) {
            PaymentDTO paymentDTO = new PaymentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            );
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException {
        boolean isPaymentUpdated = CrudUtil.execute(
                "update payment set method=?, date_paid=?, ad_id=? where payment_id=?",
                paymentDTO.getPaymentMethod(),
                paymentDTO.getPaidDate(),
                paymentDTO.getAdminId(),
                paymentDTO.getPaymentId()
        );

        if (!isPaymentUpdated) {
            return false;
        }

        return CrudUtil.execute(
                "update payment_plan set method=?, price=?, discount=?, discounted_price=?, st_id=? where plan_id=?",
                paymentDTO.getPlanMethod(),
                paymentDTO.getPrice(),
                paymentDTO.getDiscount(),
                paymentDTO.getDiscountPrice(),
                paymentDTO.getStudentId(),
                paymentDTO.getPlanId()
        );
    }

    public boolean deletePayment(String paymentId) throws SQLException {
        CrudUtil.execute("delete from payment_plan where pay_id=?", paymentId);
        return CrudUtil.execute("delete from payment where payment_id=?", paymentId);
    }

    public ArrayList<String> getAllPaymentId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select payment_id from payment");
        ArrayList<String> paymentIds = new ArrayList<>();
        while (rst.next()) {
            paymentIds.add(rst.getString(1));
        }
        return paymentIds;
    }

    public PaymentDTO findById(String selectedPaymentId) throws SQLException {
        ResultSet rst = CrudUtil.execute(
                "select p.payment_id, p.method, p.date_paid, p.ad_id, pp.plan_id, pp.method, pp.price, pp.discount, pp.discounted_price, pp.st_id " +
                        "from payment p join payment_plan pp on p.payment_id = pp.pay_id where p.payment_id=?",
                selectedPaymentId
        );

        if (rst.next()) {
            return new PaymentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            );
        }
        return null;
    }

    public ArrayList<String> getAllPaymentOptions() {
        return new ArrayList<>(List.of("Cash", "Card"));
    }

    public ArrayList<String> getAllDiscountOptions() {
        return new ArrayList<>(List.of("0", "10", "20"));
    }
}
