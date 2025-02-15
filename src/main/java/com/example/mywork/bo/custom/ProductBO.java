package com.example.mywork.bo.custom;
import com.example.mywork.bo.SuperBO;
import com.example.mywork.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {

    String getNextProductId() throws SQLException;

    boolean save(Product DTO) throws SQLException;

    ArrayList<Product> getAll() throws SQLException;

    boolean update(Product DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;

}
