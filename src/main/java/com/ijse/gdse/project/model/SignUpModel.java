package com.ijse.gdse.project.model;

import com.ijse.gdse.project.dto.SignUpDTO;
import com.ijse.gdse.project.util.CrudUtil;

import java.sql.SQLException;

public class SignUpModel {
    public boolean saveSignIn(SignUpDTO signUpDTO) throws SQLException {
        return CrudUtil.execute("insert into signup values(?,?,?,?)",
                signUpDTO.getUsername(),
                signUpDTO.getName(),
                signUpDTO.getAddress(),
                signUpDTO.getPassword()
        );
    }
}
