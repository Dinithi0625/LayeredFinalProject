package com.example.mywork.bo.custom;
import com.example.mywork.bo.SuperBO;
import com.example.mywork.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    String getNextCustomerId() throws SQLException;

    boolean save(Customer DTO) throws SQLException;

    ArrayList<Customer> getAll() throws SQLException;

    boolean update(Customer DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;
}
