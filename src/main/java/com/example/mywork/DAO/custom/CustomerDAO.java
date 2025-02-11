package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.entity.Customer;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    String getNextCustomerId() throws SQLException;

}
