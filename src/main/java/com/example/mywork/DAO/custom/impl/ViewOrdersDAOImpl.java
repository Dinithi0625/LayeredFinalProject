package com.example.mywork.DAO.custom.impl;

import com.example.mywork.db.DBConnection;
import com.example.mywork.dto.tm.OrderProductTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViewOrdersDAOImpl {

    public List<OrderProductTM> getOrderData() throws Exception {
        List<OrderProductTM> orderList = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT orderId, productId, date, qty, price FROM orderproduct";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            orderList.add(new OrderProductTM(
                    resultSet.getString("orderId"),
                    resultSet.getString("productId"),
                    resultSet.getString("date"),
                    resultSet.getInt("qty"),
                    resultSet.getDouble("price")
            ));
        }
        return orderList;
    }
}
