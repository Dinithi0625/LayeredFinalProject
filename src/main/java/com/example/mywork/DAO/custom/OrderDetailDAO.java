package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;

public interface OrderDetailDAO extends CrudDAO {
    String getNextOrderId();
}
