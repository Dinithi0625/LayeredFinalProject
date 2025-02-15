package com.example.mywork.dao.custom;
import com.example.mywork.dao.CrudDAO;
import com.example.mywork.dto.tm.CartTM;
import com.example.mywork.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<Order> {

     List<CartTM> loadtDetails() throws SQLException;

     double getAllPrice() throws SQLException;

     double getpriceqty(String mm) throws SQLException;


}
