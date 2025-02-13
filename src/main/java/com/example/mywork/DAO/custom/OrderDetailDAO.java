package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    String getNextOrderId() throws SQLException;
}
