package com.example.mywork.dao.custom;
import com.example.mywork.dao.CrudDAO;
import com.example.mywork.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    String getNextOrderId() throws SQLException;
}
