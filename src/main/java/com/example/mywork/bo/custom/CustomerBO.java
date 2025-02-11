package com.example.mywork.bo.custom;

import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    String getNextCustomerId() throws SQLException;

    boolean save(CustomerDTO DTO) throws SQLException;

    ArrayList<CustomerDTO> getAll() throws SQLException;

    boolean update(CustomerDTO DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;
}
