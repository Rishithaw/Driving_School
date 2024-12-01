package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.SalaryDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public String getNextSalaryId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select salary_id from salary order by salary_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("Sa%03d", newIndex);
        }
        return "Sa001";
    }

    public boolean saveSalary(SalaryDTO salaryDTO) throws SQLException {
        return CrudUtil.execute("insert into salary values(?,?,?,?,?,?)",
                salaryDTO.getSalaryId(),
                salaryDTO.getSalary(),
                salaryDTO.getPayDay(),
                salaryDTO.getReceived(),
                salaryDTO.getNoOfHolidays(),
                salaryDTO.getStaffId()
        );
    }

    public ArrayList<SalaryDTO> getAllSalary() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from salary");
        ArrayList<SalaryDTO> salaryDTOS = new ArrayList<>();
        while (rst.next()) {
            SalaryDTO salaryDTO = new SalaryDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
            salaryDTOS.add(salaryDTO);
        }
        return salaryDTOS;
    }

    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException {
        return CrudUtil.execute(
                "update salary set amount=?, pay_Day=?, is_Received=?, no_Of_Holidays=?, staff_id=? where salary_id=?",
                salaryDTO.getSalaryId(),
                salaryDTO.getSalary(),
                salaryDTO.getPayDay(),
                salaryDTO.getReceived(),
                salaryDTO.getNoOfHolidays(),
                salaryDTO.getStaffId()
        );
    }

    public boolean deleteSalary(String salaryId) throws SQLException {
        return CrudUtil.execute("delete from salary where salary_id=?",salaryId);
    }

    public ArrayList<String> getAllSalaryId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select salary_id from salary");
        ArrayList<String> salaryIds = new ArrayList<>();
        while (rst.next()) {
            salaryIds.add(rst.getString(1));
        }
        return salaryIds;
    }

    public SalaryDTO findById(String selectedSalId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from salary where salary_id=?",selectedSalId);

        if (rst.next()) {
            return new SalaryDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    public ArrayList<String> getAllSalaryStatus() {
        ArrayList<String> status = new ArrayList<>(List.of("Pending", "Paid"));
        return status;
    }
}
