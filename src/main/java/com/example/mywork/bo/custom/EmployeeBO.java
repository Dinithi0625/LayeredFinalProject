package com.example.mywork.bo.custom;

import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    String getNextEmployeeId() throws SQLException;

    boolean save(EmployeeDTO DTO) throws SQLException;

    ArrayList<EmployeeDTO> getAll() throws SQLException;

    boolean update(EmployeeDTO DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;
}
