package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.ServiceDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceModel {
    public String getNextServiceId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select service_id from service order by service_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIndex = i + 1;
            return String.format("Se%03d", newIndex);
        }
        return "Se001";
    }

    public boolean saveService(ServiceDTO serviceDTO) throws SQLException {
        return CrudUtil.execute("insert into service values(?,?,?,?,?)",
                serviceDTO.getServiceId(),
                serviceDTO.getServiceDate(),
                serviceDTO.getReason(),
                serviceDTO.getCost(),
                serviceDTO.getVehicleId()
        );
    }

    public ArrayList<ServiceDTO> getAllService() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from service");
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        while (rst.next()) {
            ServiceDTO serviceDTO = new ServiceDTO(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            serviceDTOS.add(serviceDTO);
        }
        return serviceDTOS;
    }

    public boolean updateService(ServiceDTO serviceDTO) throws SQLException {
        return CrudUtil.execute(
                "update service set service_date=?, reason=?, cost=?, vec_id=? where service_id=?",
                serviceDTO.getServiceDate(),
                serviceDTO.getReason(),
                serviceDTO.getCost(),
                serviceDTO.getVehicleId(),
                serviceDTO.getServiceId()
        );
    }

    public boolean deleteService(String serviceId) throws SQLException {
        return CrudUtil.execute("delete from service where service_id=?", serviceId);
    }

    public ArrayList<String> getAllServiceId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select service_id from service");
        ArrayList<String> serviceIds = new ArrayList<>();
        while (rst.next()) {
            serviceIds.add(rst.getString(1));
        }
        return serviceIds;
    }

    public ServiceDTO findById(String selectedServiceId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from service where service_id=?", selectedServiceId);

        if (rst.next()) {
            return new ServiceDTO(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }
}
