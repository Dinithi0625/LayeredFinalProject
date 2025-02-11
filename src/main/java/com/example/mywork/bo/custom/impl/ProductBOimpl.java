package com.example.mywork.bo.custom.impl;

import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.ProductDAO;
import com.example.mywork.bo.custom.ProductBO;
import com.example.mywork.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOimpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    @Override
    public String getNextProductId() throws SQLException {
        return productDAO.getNextProductId();
    }

    @Override
    public boolean save(ProductDTO DTO) throws SQLException {
        return productDAO.save(DTO);
    }

    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException {
        return productDAO.getAll();
    }

    @Override
    public boolean update(ProductDTO DTO) throws SQLException {
        return productDAO.update(DTO);
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return productDAO.delete(Id);
    }

}
