package com.example.mywork.bo.custom;

import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.OrdersDTO;
import com.example.mywork.dto.tm.CartTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrdersBO extends SuperBO {

    List<CartTM> loadtDetails() throws SQLException;

    double getAllPrice() throws SQLException;

    double getpriceqty(String mm) throws SQLException;

    boolean save(OrdersDTO DTO) throws SQLException;

    ArrayList<OrdersDTO> getAll() throws SQLException;

    ArrayList<String> getAllICustomerIds() throws SQLException;

    ArrayList<String> getAllIProductIds() throws SQLException;

}
