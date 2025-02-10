package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.dto.ProductDTO;
import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<ProductDTO> {

    double getProductPrice(String productId) throws SQLException;

    String getNextProductId() throws SQLException;

}
