package com.example.mywork.dao.custom;

import com.example.mywork.dao.CrudDAO;
import com.example.mywork.entity.Product;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product> {

    double getProductPrice(String productId) throws SQLException;

    String getNextProductId() throws SQLException;

}
