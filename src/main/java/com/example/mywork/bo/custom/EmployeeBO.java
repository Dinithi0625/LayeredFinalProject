package com.example.mywork.bo.custom;
import com.example.mywork.bo.SuperBO;
import com.example.mywork.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    String getNextEmployeeId() throws SQLException;

    boolean save(Employee DTO) throws SQLException;

    ArrayList<Employee> getAll() throws SQLException;

    boolean update(Employee DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;
}
