package com.example.mywork.dao.custom.impl;

import com.example.mywork.dao.SqlUtil;
import com.example.mywork.dao.custom.OrderDetailDAO;
import com.example.mywork.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public String getNextOrderId() throws SQLException {
        ResultSet rst = SqlUtil.execute("select orderId from orders order by orderId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("O%03d", newIdIndex);
        }
        return "O001";
    }

    @Override
    public boolean save(OrderDetail DTO) throws SQLException {
        return SqlUtil.execute("INSERT INTO orderproduct (orderId, productId, date, qty, price) VALUES (?, ?, ?, ?, ?)",
                DTO.getOrderId(),
                DTO.getProductId(),
                DTO.getDate(),
                DTO.getQty(),
                DTO.getPrice()
        );
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDetail DTO) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }
}
