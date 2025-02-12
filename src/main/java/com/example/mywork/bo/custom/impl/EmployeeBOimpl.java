package com.example.mywork.bo.custom.impl;
import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.EmployeeDAO;
import com.example.mywork.bo.custom.EmployeeBO;
import com.example.mywork.dto.EmployeeDTO;
import com.example.mywork.entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOimpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public String getNextEmployeeId() throws SQLException {
        return employeeDAO.getNextEmployeeId();
    }

    @Override
    public boolean save(EmployeeDTO DTO) throws SQLException {
        return employeeDAO.save(new Employee(DTO.getEmployeeId(),DTO.getName(),DTO.getAddress(),DTO.getContact()));
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ArrayList<Employee> employees =employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOS.add(new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getContact()));
        }
        return employeeDTOS;
    }

    @Override
    public boolean update(EmployeeDTO DTO) throws SQLException {
        return employeeDAO.update(new Employee(DTO.getEmployeeId(),DTO.getName(),DTO.getAddress(),DTO.getContact()));
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return employeeDAO.delete(Id);
    }
}
