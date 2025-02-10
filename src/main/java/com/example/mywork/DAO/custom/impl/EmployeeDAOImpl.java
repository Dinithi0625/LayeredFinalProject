package com.example.mywork.DAO.custom.impl;

import com.example.mywork.DAO.custom.EmployeeDAO;
import com.example.mywork.dto.EmployeeDTO;
import com.example.mywork.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public String getNextEmployeeId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select employeeId from employee order by employeeId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("E%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "E001"; // Return the default customer ID if no data is found
    }

    public boolean save(EmployeeDTO employeeDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into employee values (?,?,?,?)",
                employeeDTO.getEmployeeId(),
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getContact()
        );
    }

    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from employee");

        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Address
                    rst.getString(4)   // Contact
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public boolean update(EmployeeDTO employeeDTO) throws SQLException {
        return CrudUtil.execute(
                "update employee set name=?, address=?, contact=? where employeeId=?",
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getContact(),
                employeeDTO.getEmployeeId()
        );
    }


    public boolean delete(String employeeId) throws SQLException {
        return CrudUtil.execute("delete from employee where employeeId=?", employeeId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select employeeId from employee");

        ArrayList<String> employeeIds = new ArrayList<>();

        while (rst.next()) {
            employeeIds.add(rst.getString(1));
        }

        return employeeIds;
    }

}

