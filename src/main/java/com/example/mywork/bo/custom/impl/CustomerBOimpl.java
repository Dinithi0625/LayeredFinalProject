package com.example.mywork.bo.custom.impl;

import com.example.mywork.bo.custom.CustomerBO;
import com.example.mywork.dao.DAOFactory;
import com.example.mywork.dao.custom.CustomerDAO;
import com.example.mywork.dto.CustomerDTO;
import com.example.mywork.entity.Customer;

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
        return customerDAO.save(new Customer(DTO.getCustomerId(),DTO.getName(),DTO.getAddress(),DTO.getContact()));
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : all) {
            customerDTOs.add(new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getContact()));
        }
        return customerDTOs;
    }

    @Override
    public boolean update(CustomerDTO DTO) throws SQLException {
        return customerDAO.update(new Customer(DTO.getCustomerId(),DTO.getName(),DTO.getAddress(),DTO.getContact()));
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return customerDAO.delete(Id);
    }
}
