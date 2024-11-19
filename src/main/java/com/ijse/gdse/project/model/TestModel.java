package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.StudentDTO;
import com.ijse.gdse.project.dto.TestDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestModel {
    public String getNextTestId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select test_id from test order by test_id desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIndex = i+1;
            return String.format("T%03d", newIndex);
        }
        return "T001";
    }

    public boolean saveTest(TestDTO testDTO) throws SQLException {
        return CrudUtil.execute("insert into test values(?,?,?,?,?)",
                testDTO.getTestId(),
                testDTO.getDate(),
                testDTO.getTime(),
                testDTO.getStudentId(),
                testDTO.getInstructorId()
        );
    }

    public ArrayList<TestDTO> getAllTest() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from test");
        ArrayList<TestDTO> testDTOS = new ArrayList<>();
        while (rst.next()) {
            TestDTO testDTO = new TestDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            testDTOS.add(testDTO);
        }
        return testDTOS;
    }

    public boolean updateTest(TestDTO testDTO) throws SQLException {
        return CrudUtil.execute(
                "update test set date=?, time=?, st_id=?, in_id=? where test_id=?",
                testDTO.getDate(),
                testDTO.getTime(),
                testDTO.getStudentId(),
                testDTO.getInstructorId(),
                testDTO.getTestId()
        );
    }

    public boolean deleteTest(String studentId) throws SQLException {
        return CrudUtil.execute("delete from test where test_id=?",studentId);
    }
}
