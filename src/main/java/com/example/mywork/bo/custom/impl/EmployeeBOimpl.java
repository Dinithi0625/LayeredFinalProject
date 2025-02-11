package com.example.mywork.bo.custom.impl;
import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.EmployeeDAO;
import com.example.mywork.bo.custom.EmployeeBO;
import com.example.mywork.dto.EmployeeDTO;
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
        return employeeDAO.save(DTO);
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        return employeeDAO.getAll();
    }

    @Override
    public boolean update(EmployeeDTO DTO) throws SQLException {
        return employeeDAO.update(DTO);
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return employeeDAO.delete(Id);
    }
}
