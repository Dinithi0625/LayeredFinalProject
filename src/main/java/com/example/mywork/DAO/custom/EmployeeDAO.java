package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.entity.Employee;
import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {

    String getNextEmployeeId() throws SQLException;

}
