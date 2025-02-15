package com.example.mywork.bo.custom;

import com.example.mywork.bo.SuperBO;
import com.example.mywork.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    String getNextOrderId() throws SQLException;

    boolean save(OrderDetail DTO) throws SQLException;

    ArrayList<OrderDetail> getAll() throws SQLException;

    boolean update(OrderDetail DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;
}
