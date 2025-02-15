package com.example.mywork.dao.custom.impl;

import com.example.mywork.dao.SqlUtil;
import com.example.mywork.dao.custom.EmployeeDAO;
import com.example.mywork.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public String getNextEmployeeId() throws SQLException {
        ResultSet rst = SqlUtil.execute("select employeeId from employee order by employeeId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("E%03d", newIdIndex);
        }
        return "E001";
    }

    public boolean save(Employee employeeDTO) throws SQLException {
        return SqlUtil.execute(
                "insert into employee values (?,?,?,?)",
                employeeDTO.getEmployeeId(),
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getContact()
        );
    }

    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = SqlUtil.execute("select * from employee");

        ArrayList<Employee> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            Employee employeeDTO = new Employee(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Address
                    rst.getString(4)   // Contact
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public boolean update(Employee employeeDTO) throws SQLException {
        return SqlUtil.execute(
                "update employee set name=?, address=?, contact=? where employeeId=?",
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getContact(),
                employeeDTO.getEmployeeId()
        );
    }


    public boolean delete(String employeeId) throws SQLException {
        return SqlUtil.execute("delete from employee where employeeId=?", employeeId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SqlUtil.execute("select employeeId from employee");

        ArrayList<String> employeeIds = new ArrayList<>();

        while (rst.next()) {
            employeeIds.add(rst.getString(1));
        }

        return employeeIds;
    }

}

