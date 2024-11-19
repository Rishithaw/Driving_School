package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.VehicleDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public String getNextVehicleId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select vec_id from vehicle order by vec_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("V%03d", newIndex);
        }
        return "V001";
    }

    public boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException {
        boolean isSaved = CrudUtil.execute("insert into vehicle values(?,?,?,?,?,?)",
                vehicleDTO.getVehicleId(),
                vehicleDTO.getVehicleType(),
                vehicleDTO.getLessonFee(),
                vehicleDTO.getAdmin(),
                vehicleDTO.getInstructor(),
                vehicleDTO.getMechanic()
                );
        return isSaved;
    }

    public boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException {
        return CrudUtil.execute("update vehicle set vehicle_type=?, lesson_fee=?, ad_id=?,in_id=?, mec_id=? where vec_id=?",
                vehicleDTO.getVehicleType(),
                vehicleDTO.getLessonFee(),
                vehicleDTO.getAdmin(),
                vehicleDTO.getInstructor(),
                vehicleDTO.getMechanic(),
                vehicleDTO.getVehicleId()
        );
    }

    public boolean deleteVehicle(String vehicleId) throws SQLException {
        return CrudUtil.execute("delete from vehicle where vec_id=?", vehicleId);
    }

    public ArrayList<VehicleDTO> getAllVehicles() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from vehicle");
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        while (rst.next()) {
            VehicleDTO vehicleDTO = new VehicleDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }


}
