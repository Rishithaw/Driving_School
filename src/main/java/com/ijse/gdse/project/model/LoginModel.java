package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.SignUpDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginModel {
    public ArrayList<SignUpDTO> getCredentials() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from signup");
        ArrayList<SignUpDTO> signUpDTOS = new ArrayList<>();
        while (rst.next()) {
            SignUpDTO signUpDTO = new SignUpDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            signUpDTOS.add(signUpDTO);
        }
        return signUpDTOS;
    }
}
