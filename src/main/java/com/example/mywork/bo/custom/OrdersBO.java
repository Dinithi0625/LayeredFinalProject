package com.example.mywork.bo.custom;
import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.tm.CartTM;
import com.example.mywork.entity.Order;
import com.example.mywork.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrdersBO extends SuperBO {

    List<CartTM> loadtDetails() throws SQLException;

    double getAllPrice() throws SQLException;

    double getpriceqty(String mm) throws SQLException;

    boolean save(Order DTO) throws SQLException;

    ArrayList<Order> getAll() throws SQLException;

    ArrayList<String> getAllICustomerIds() throws SQLException;

    ArrayList<String> getAllIProductIds() throws SQLException;

    String insertAll(Order ordersDTO, OrderDetail orderDetailDTO, String productId) throws SQLException;



    }
