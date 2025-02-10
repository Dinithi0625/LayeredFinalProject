package com.example.mywork.DAO.custom.impl;
import com.example.mywork.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
public class OrderDetailDAOImpl {

    public String getNextOrderId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select orderId from orders order by orderId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("O%03d", newIdIndex);
        }
        return "O001";
    }

}
