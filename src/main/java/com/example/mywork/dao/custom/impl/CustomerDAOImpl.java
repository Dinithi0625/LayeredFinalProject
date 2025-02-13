package com.example.mywork.dao.custom.impl;

import com.example.mywork.dao.SqlUtil;
import com.example.mywork.dao.custom.CustomerDAO;
import com.example.mywork.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public String getNextCustomerId() throws SQLException {

        ResultSet rst = SqlUtil.execute("select custId from customer order by custId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }

    public boolean save(Customer customerDTO) throws SQLException {
            return SqlUtil.execute(
                "insert into customer values (?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact()
        );
    }

    public ArrayList<Customer> getAll() throws SQLException {

        ResultSet rst = SqlUtil.execute("select * from customer");
        ArrayList<Customer> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            Customer customerDTO = new Customer(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Address
                    rst.getString(4)   // Contact
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public boolean update(Customer customerDTO) throws SQLException {
        return SqlUtil.execute(
                "update customer set Name=?, Address=?, contact=? where custId=?",
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact(),
                customerDTO.getCustomerId()
        );
    }

    public boolean delete(String customerId) throws SQLException {
        return SqlUtil.execute("delete from customer where custId=?", customerId);
    }

    public ArrayList<String> getAllIds() throws SQLException {

        ResultSet rst = SqlUtil.execute("select custId from customer");
        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }
        return customerIds;
    }
}

