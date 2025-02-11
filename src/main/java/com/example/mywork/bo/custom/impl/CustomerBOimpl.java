package com.example.mywork.bo.custom.impl;

import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.CustomerDAO;
import com.example.mywork.bo.custom.CustomerBO;
import com.example.mywork.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOimpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getNextCustomerId() throws SQLException {
        return customerDAO.getNextCustomerId();
    }

    @Override
    public boolean save(CustomerDTO DTO) throws SQLException {
        return customerDAO.save(DTO);
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException {
        return customerDAO.getAll();
    }

    @Override
    public boolean update(CustomerDTO DTO) throws SQLException {
        return customerDAO.update(DTO);
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return customerDAO.delete(Id);
    }
}
