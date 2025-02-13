package com.example.mywork.dao.custom;

import com.example.mywork.dao.CrudDAO;
import com.example.mywork.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    String getNextCustomerId() throws SQLException;

}
