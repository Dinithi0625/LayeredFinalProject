package com.example.mywork.DAO.custom.impl;

import com.example.mywork.DAO.custom.CustomerDAO;
import com.example.mywork.dto.CustomerDTO;
import com.example.mywork.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public String getNextCustomerId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select custId from customer order by custId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; // Return the default customer ID if no data is found
    }

    public boolean save(CustomerDTO customerDTO) throws SQLException {
            return CrudUtil.execute(
                "insert into customer values (?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact()
        );
    }

    public ArrayList<CustomerDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Address
                    rst.getString(4)   // Contact
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public boolean update(CustomerDTO customerDTO) throws SQLException {
        return CrudUtil.execute(
                "update customer set Name=?, Address=?, contact=? where custId=?",
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact(),
                customerDTO.getCustomerId()
        );
    }

    public boolean delete(String customerId) throws SQLException {
        return CrudUtil.execute("delete from customer where custId=?", customerId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select custId from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }
}

