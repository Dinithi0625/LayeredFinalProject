package com.example.mywork.dao.custom;

import com.example.mywork.dao.CrudDAO;
import com.example.mywork.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {

    String getNextEmployeeId() throws SQLException;

}
