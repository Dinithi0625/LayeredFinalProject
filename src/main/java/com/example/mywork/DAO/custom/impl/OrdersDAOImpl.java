package com.example.mywork.DAO.custom.impl;
import com.example.mywork.DAO.custom.OrdersDAO;
import com.example.mywork.db.DBConnection;
import com.example.mywork.dto.OrderDetailDTO;
import com.example.mywork.dto.OrdersDTO;
import com.example.mywork.dto.PaymentDTO;
import com.example.mywork.dto.tm.CartTM;
import com.example.mywork.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl{

    public String insertAll(OrdersDTO ordersDTO, OrderDetail orderDetailDTO, String productId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);

            String sql = "SELECT qty FROM product WHERE productId = ?";
            PreparedStatement checkStockStmt = connection.prepareStatement(sql);
            checkStockStmt.setString(1, productId);
            ResultSet resultSet = checkStockStmt.executeQuery();

            if (!resultSet.next()) {
                connection.rollback();
                return "Product not found";
            }

            int stockCount = resultSet.getInt("qty");
            int orderedQty = orderDetailDTO.getQty();

            if (orderedQty > stockCount) {
                connection.rollback();
                return "Insufficient stock";
            }

            String updateStockSql = "UPDATE product SET qty = qty - ? WHERE productId = ?";
            PreparedStatement updateStockStmt = connection.prepareStatement(updateStockSql);
            updateStockStmt.setInt(1, orderedQty);
            updateStockStmt.setString(2, productId);

            if (!(updateStockStmt.executeUpdate() > 0)) {
                connection.rollback();
                return "Failed to update stock";
            }


            String insertOrderSql = "INSERT INTO orders (orderId, date, custId) VALUES (?, ?, ?)";
            PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderSql);
            insertOrderStmt.setString(1, ordersDTO.getOrderId());
            insertOrderStmt.setString(2, ordersDTO.getDate());
            insertOrderStmt.setString(3, ordersDTO.getCustId());

            if (!(insertOrderStmt.executeUpdate() > 0)) {
                connection.rollback();
                return "Failed to save order";
            }

            String insertOrderDetailSql = "INSERT INTO orderproduct (orderId, productId, date, qty, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertOrderDetailStmt = connection.prepareStatement(insertOrderDetailSql);
            insertOrderDetailStmt.setString(1, orderDetailDTO.getOrderId());
            insertOrderDetailStmt.setString(2, orderDetailDTO.getProductId());
            insertOrderDetailStmt.setString(3, orderDetailDTO.getDate());
            insertOrderDetailStmt.setInt(4, orderedQty);
            insertOrderDetailStmt.setDouble(5, orderDetailDTO.getPrice());

            if (!(insertOrderDetailStmt.executeUpdate() > 0)) {
                connection.rollback();
                return "Failed to save order details";
            }
            connection.commit();
            return "Order saved successfully";

        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException("Error occurred: " + e.getMessage(), e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<CartTM> loadtDetails() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select o.custId,t.productId,t.qty,t.date,t.price from orders o join orderproduct t ON o.orderId=t.orderId";
        PreparedStatement statement = connection.prepareStatement(sql);
        List<CartTM> sli = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            CartTM cartTM = new CartTM(
              resultSet.getString(1),
              resultSet.getString(2),
              resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );
            sli.add(cartTM);
        }
        return sli;
    }

    public double getAllPrice() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select sum(price) from orderproduct";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;
    }

    public double getpriceqty(String mm) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select price from product where productId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,mm);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;
    }

    public String insertPayment(PaymentDTO paymentDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO payment(Amount, Date, orderId) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, paymentDTO.getAmount());
            statement.setString(2, paymentDTO.getDate());
            statement.setString(3, paymentDTO.getOrderId());
            int resp = statement.executeUpdate();
            connection.commit();
            return resp > 0 ? "success" : "fail";

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }


}






