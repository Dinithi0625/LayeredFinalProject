package com.example.mywork.bo.custom;

import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    String getNextOrderId() throws SQLException;

    boolean save(OrderDetailDTO DTO) throws SQLException;

    ArrayList<OrderDetailDTO> getAll() throws SQLException;

    boolean update(OrderDetailDTO DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;
}
