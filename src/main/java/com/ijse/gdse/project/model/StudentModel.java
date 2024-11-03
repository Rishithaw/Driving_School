package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentModel {
    public String getNextCustomerId() throws SQLException{
        ResultSet rst = CrudUtil.execute("select student_id from student order by student_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("S%03d", newIndex);
        }
        return "S001";
    }

    public boolean saveStudent(StudentDTO studentDTO) throws SQLException {
        boolean isSaved = CrudUtil.execute("insert into student values(?,?,?,?,?,?,?,?,?)");
                studentDTO.getStudent_id();
                studentDTO.getDOB();
                studentDTO.getNIC();
                studentDTO.getName();
                studentDTO.getAddress();
                studentDTO.getGender();
                studentDTO.getHelping_aids();
                studentDTO.getAdvance_payment();
                studentDTO.getEmail();
        return isSaved;
    }
}
