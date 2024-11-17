package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentModel {

    public String getNextStudentId() throws SQLException{
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
        boolean isSaved = CrudUtil.execute("insert into student values(?,?,?,?,?,?,?,?,?,?)",
                studentDTO.getStudentId(),
                studentDTO.getName(),
                studentDTO.getNIC(),
                studentDTO.getDOB(),
                studentDTO.getGender(),
                studentDTO.getAddress(),
                studentDTO.getAssists(),
                studentDTO.getEmail(),
                studentDTO.getAdvancePayment(),
                studentDTO.getVehicleId()
        );
        return isSaved;
    }

    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from student");
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        while (rst.next()) {
            StudentDTO studentDTO = new StudentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            );
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public boolean updateStudent(StudentDTO studentDTO) throws SQLException {
        return CrudUtil.execute(
                "update student set DOB=?, NIC=?, name=?, address=?, gender=?, helping_aids=?, advance_payment=?, email=?, vec_id=? where student_id=?",
                studentDTO.getDOB(),
                studentDTO.getNIC(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getGender(),
                studentDTO.getAssists(),
                studentDTO.getAdvancePayment(),
                studentDTO.getEmail(),
                studentDTO.getVehicleId(),
                studentDTO.getStudentId()
        );
    }

    public boolean deleteStudent(String studentId) throws SQLException {
        return CrudUtil.execute("delete from student where student_id=?",studentId);
    }

    public ArrayList<String> getAllStudentId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select student_id from student");
        ArrayList<String> studentIds = new ArrayList<>();
        while (rst.next()) {
            studentIds.add(rst.getString(1));
        }
        return studentIds;
    }

    public StudentDTO findById(String selectedStuId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from student where student_id=?",selectedStuId);

        if (rst.next()) {
            return new StudentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
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
}
