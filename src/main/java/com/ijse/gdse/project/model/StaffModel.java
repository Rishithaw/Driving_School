package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.StaffDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffModel {
    public String getNextStaffId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select staff_id from staff order by staff_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("St%03d", newIndex);
        }
        return "St001";
    }

    public boolean saveStaff(StaffDTO staffDTO) throws SQLException {
        return CrudUtil.execute("insert into staff values(?,?,?,?)",
                staffDTO.getStaffId(),
                staffDTO.getName(),
                staffDTO.getRole(),
                staffDTO.getId()
        );
    }

    public ArrayList<StaffDTO> getAllStaff() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from staff");
        ArrayList<StaffDTO> staffDTOS = new ArrayList<>();
        while (rst.next()) {
            StaffDTO staffDTO = new StaffDTO(
            rst.getString(1),
            rst.getString(2),
            rst.getString(3),
            rst.getString(4)
            );
            staffDTOS.add(staffDTO);
        }
        return staffDTOS;
    }

    public boolean updateStaff(StaffDTO staffDTO) throws SQLException {
        return CrudUtil.execute("update staff set role=?, ID=?, name=? where staff_id=?",
                staffDTO.getRole(),
                staffDTO.getId(),
                staffDTO.getName(),
                staffDTO.getStaffId()
        );
    }

    public boolean deleteStaff(String staffId) throws SQLException {
        return CrudUtil.execute("delete from staff where staff_id=?",staffId);
    }
}
