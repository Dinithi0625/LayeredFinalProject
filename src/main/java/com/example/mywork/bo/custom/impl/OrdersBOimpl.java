package com.example.mywork.bo.custom.impl;

import com.example.mywork.bo.custom.OrdersBO;
import com.example.mywork.dao.DAOFactory;
import com.example.mywork.dao.custom.CustomerDAO;
import com.example.mywork.dao.custom.OrdersDAO;
import com.example.mywork.dao.custom.ProductDAO;
import com.example.mywork.dto.OrdersDTO;
import com.example.mywork.dto.tm.CartTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOimpl implements OrdersBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);

    @Override
    public List<CartTM> loadtDetails() throws SQLException {
        return ordersDAO.loadtDetails();
    }

    @Override
    public double getAllPrice() throws SQLException {
        return 0;
    }

    @Override
    public double getpriceqty(String mm) throws SQLException {
        return ordersDAO.getpriceqty(mm);
    }

    @Override
    public boolean save(OrdersDTO DTO) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrdersDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllICustomerIds() throws SQLException {
        return customerDAO.getAllIds();
    }

    @Override
    public ArrayList<String> getAllIProductIds() throws SQLException {
        return productDAO.getAllIds();
    }


}
