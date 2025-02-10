package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.dto.OrderDetailDTO;
import com.example.mywork.dto.OrdersDTO;
import com.example.mywork.dto.PaymentDTO;
import com.example.mywork.dto.tm.CartTM;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO {

    String insertAll(OrdersDTO ordersDTO, OrderDetailDTO orderDetailDTO, String productId) throws SQLException;

    List<CartTM> loadtDetails() throws SQLException;

    double getAllPrice() throws SQLException;

    double getpriceqty(String mm) throws SQLException;

    String insertPayment(PaymentDTO paymentDTO) throws SQLException;
}
