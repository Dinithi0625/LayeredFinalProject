package com.example.mywork.bo.custom.impl;

import com.example.mywork.bo.custom.OrdersBO;
import com.example.mywork.dao.DAOFactory;
import com.example.mywork.dao.custom.CustomerDAO;
import com.example.mywork.dao.custom.OrderDetailDAO;
import com.example.mywork.dao.custom.OrdersDAO;
import com.example.mywork.dao.custom.ProductDAO;
import com.example.mywork.db.DBConnection;
import com.example.mywork.dto.tm.CartTM;
import com.example.mywork.entity.Order;
import com.example.mywork.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOimpl implements OrdersBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERPRODUCT);

    @Override
    public List<CartTM> loadtDetails() throws SQLException {
        return ordersDAO.loadtDetails();
    }

    @Override
    public double getAllPrice() throws SQLException {
        return ordersDAO.getAllPrice();
    }

    @Override
    public double getpriceqty(String mm) throws SQLException {
        return ordersDAO.getpriceqty(mm);
    }

    @Override
    public boolean save(Order DTO) throws SQLException {
        return ordersDAO.save(DTO);
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException {
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

    public String insertAll(Order ordersDTO, OrderDetail orderDetailDTO, String productId) throws SQLException {
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


//            String insertOrderSql = "INSERT INTO orders (orderId, date, custId) VALUES (?, ?, ?)";
//            PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderSql);
//            insertOrderStmt.setString(1, ordersDTO.getOrderId());
//            insertOrderStmt.setString(2, ordersDTO.getDate());
//            insertOrderStmt.setString(3, ordersDTO.getCustId());
//
//            if (!(insertOrderStmt.executeUpdate() > 0)) {
//                connection.rollback();
//                return "Failed to save order";
//            }
            boolean isSaveOrder = ordersDAO.save(ordersDTO);
            if (!isSaveOrder) {
                connection.rollback();
                return "Order not saved";
            }


//            String insertOrderDetailSql = "INSERT INTO orderproduct (orderId, productId, date, qty, price) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement insertOrderDetailStmt = connection.prepareStatement(insertOrderDetailSql);
//            insertOrderDetailStmt.setString(1, orderDetailDTO.getOrderId());
//            insertOrderDetailStmt.setString(2, orderDetailDTO.getProductId());
//            insertOrderDetailStmt.setString(3, orderDetailDTO.getDate());
//            insertOrderDetailStmt.setInt(4, orderedQty);
//            insertOrderDetailStmt.setDouble(5, orderDetailDTO.getPrice());
//
//            if (!(insertOrderDetailStmt.executeUpdate() > 0)) {
//                connection.rollback();
//                return "Failed to save order details";
//            }

            boolean isSaveOrderProduct = orderDetailDAO.save(orderDetailDTO);
            if (!isSaveOrderProduct) {
                connection.rollback();
                return "Order not saved";
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

}
