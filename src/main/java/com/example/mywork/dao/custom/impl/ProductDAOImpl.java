package com.example.mywork.dao.custom.impl;

import com.example.mywork.dao.SqlUtil;
import com.example.mywork.dao.custom.ProductDAO;
import com.example.mywork.db.DBConnection;
import com.example.mywork.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    public double getProductPrice(String productId) throws SQLException {
        String query = "SELECT price FROM product WHERE productId = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String priceStr = rs.getString("price");
                return Double.parseDouble(priceStr);
            } else {
                throw new SQLException("Product not found with ID: " + productId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while fetching product price: " + e.getMessage());
        }
    }
    public String getNextProductId() throws SQLException {
        ResultSet rst = SqlUtil.execute("select productId from product order by productId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    public boolean save(Product productDTO) throws SQLException {
        return SqlUtil.execute(
                "insert into product values (?,?,?,?,?)",
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getQty()
        );
    }

    public ArrayList<Product> getAll() throws SQLException {
        ResultSet rst = SqlUtil.execute("select * from product");
        ArrayList<Product> productDTOS = new ArrayList<>();

        while (rst.next()) {
            Product productDTO = new Product(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getDouble(3),  // Address
                    rst.getString(4),
                    rst.getInt(5)// Contact
            );
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public boolean update(Product productDTO) throws SQLException {
        return SqlUtil.execute(
                "update product set name=?, price=?, description=? , qty = ? where productId=?",
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getQty(),
                productDTO.getProductId()
        );
    }


    public boolean delete(String productId) throws SQLException {
        return SqlUtil.execute("delete from product where productId=?", productId);
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SqlUtil.execute("select productId from product");
        ArrayList<String> productId = new ArrayList<>();

        while (rst.next()) {
            productId.add(rst.getString(1));
        }

        return productId;
    }
//
//    public boolean checkProduct(Product product) throws SQLException {
//        SqlUtil.execute("UPDATE product SET qty = qty - ? WHERE productId = ?");
//
//    }

}

