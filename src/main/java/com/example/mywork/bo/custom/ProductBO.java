package com.example.mywork.bo.custom;

import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    String getNextProductId() throws SQLException;

    boolean save(ProductDTO DTO) throws SQLException;

    ArrayList<ProductDTO> getAll() throws SQLException;

    boolean update(ProductDTO DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;

}
