package com.example.mywork.bo.custom.impl;

import com.example.mywork.bo.custom.OrderDetailBO;
import com.example.mywork.dao.DAOFactory;
import com.example.mywork.dao.custom.OrderDetailDAO;
import com.example.mywork.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOimpl implements OrderDetailBO {

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERPRODUCT);

    @Override
    public String getNextOrderId() throws SQLException {
        return orderDetailDAO.getNextOrderId();
    }

    @Override
    public boolean save(OrderDetailDTO DTO) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDetailDTO DTO) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }
}
