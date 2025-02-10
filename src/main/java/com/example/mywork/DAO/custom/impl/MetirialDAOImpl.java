package com.example.mywork.DAO.custom.impl;

import com.example.mywork.DAO.custom.MetirialDAO;
import com.example.mywork.dto.CustomerDTO;
import com.example.mywork.dto.MetirialDTO;
import com.example.mywork.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetirialDAOImpl implements MetirialDAO {

    public String getNextMetirialId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select metirialId from metirial order by metirialId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("M%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "M001"; // Return the default customer ID if no data is found
    }

    public boolean save(MetirialDTO metirialDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into metirial values (?,?,?)",
                metirialDTO.getMetirialId(),
                metirialDTO.getName(),
                metirialDTO.getQty()
        );
    }

    public ArrayList<MetirialDTO> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from metirial");

        ArrayList<MetirialDTO> list = new ArrayList<>();
        while (rst.next()) {
            MetirialDTO metirialDTO = new MetirialDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            list.add(metirialDTO);
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return null;
    }

    public boolean update(MetirialDTO metirialDTO) throws SQLException {
        return CrudUtil.execute(
                "update metirial set  name=?, qty=? where metirialId=?",
                metirialDTO.getName(),
                metirialDTO.getQty(),
                metirialDTO.getMetirialId()
        );
    }

    public boolean delete(String metirialId) throws SQLException {
        return CrudUtil.execute("delete from metirial where metirialId=?", metirialId);
    }

}

