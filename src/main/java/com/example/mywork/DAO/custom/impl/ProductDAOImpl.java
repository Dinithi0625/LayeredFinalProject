package com.example.mywork.DAO.custom.impl;
import com.example.mywork.DAO.custom.ProductDAO;
import com.example.mywork.db.DBConnection;
import com.example.mywork.dto.ProductDTO;
import com.example.mywork.util.CrudUtil;
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
        ResultSet rst = CrudUtil.execute("select productId from product order by productId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001"; // Return the default customer ID if no data is found
    }

    public boolean save(ProductDTO productDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into product values (?,?,?,?,?)",
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getQty()
        );
    }

    public ArrayList<ProductDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from product");

        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        while (rst.next()) {
            ProductDTO productDTO = new ProductDTO(
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

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    public boolean update(ProductDTO productDTO) throws SQLException {
        return CrudUtil.execute(
                "update product set name=?, price=?, description=? , qty = ? where productId=?",
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getQty(),
                productDTO.getProductId()
        );
    }


    public boolean delete(String productId) throws SQLException {
        return CrudUtil.execute("delete from product where productId=?", productId);
    }

    public ArrayList<String> getAllProductIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select productId from product");

        ArrayList<String> productId = new ArrayList<>();

        while (rst.next()) {
            productId.add(rst.getString(1));
        }

        return productId;
    }

}

