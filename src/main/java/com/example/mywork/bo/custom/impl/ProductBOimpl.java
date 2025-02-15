package com.example.mywork.bo.custom.impl;

import com.example.mywork.bo.custom.ProductBO;
import com.example.mywork.dao.DAOFactory;
import com.example.mywork.dao.custom.ProductDAO;
import com.example.mywork.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOimpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    @Override
    public String getNextProductId() throws SQLException {
        return productDAO.getNextProductId();
    }

    @Override
    public boolean save(Product DTO) throws SQLException {
        return productDAO.save(new Product(DTO.getProductId(),DTO.getName(),DTO.getPrice(),DTO.getDescription(),DTO.getQty()));
    }

    @Override
    public ArrayList<Product> getAll() throws SQLException {
        ArrayList<Product> products = productDAO.getAll();
        ArrayList<Product> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(new Product(product.getProductId(),product.getName(),product.getPrice(),product.getDescription(),product.getQty()));
        }
        return productDTOs;
    }

    @Override
    public boolean update(Product DTO) throws SQLException {
        return productDAO.update(new Product(DTO.getProductId(),DTO.getName(),DTO.getPrice(),DTO.getDescription(),DTO.getQty()));
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return productDAO.delete(Id);
    }
}
