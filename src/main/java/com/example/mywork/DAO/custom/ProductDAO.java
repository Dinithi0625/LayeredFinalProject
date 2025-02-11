package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.entity.Product;
import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product> {

    double getProductPrice(String productId) throws SQLException;

    String getNextProductId() throws SQLException;

}
