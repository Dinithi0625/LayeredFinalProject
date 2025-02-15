package com.example.mywork.dao.custom.impl;

import com.example.mywork.dao.SqlUtil;
import com.example.mywork.dao.custom.MetirialDAO;
import com.example.mywork.entity.Metirial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetirialDAOImpl implements MetirialDAO {

    public String getNextMetirialId() throws SQLException {
        ResultSet rst = SqlUtil.execute("select metirialId from metirial order by metirialId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("M%03d", newIdIndex);
        }
        return "M001";
    }

    public boolean save(Metirial metirialDTO) throws SQLException {
        return SqlUtil.execute(
                "insert into metirial values (?,?,?)",
                metirialDTO.getMetirialId(),
                metirialDTO.getName(),
                metirialDTO.getQty()
        );
    }

    public ArrayList<Metirial> getAll() throws SQLException {
        ResultSet rst = SqlUtil.execute("select * from metirial");
        ArrayList<Metirial> list = new ArrayList<>();
        while (rst.next()) {
            Metirial metirialDTO = new Metirial(
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

    public boolean update(Metirial metirialDTO) throws SQLException {
        return SqlUtil.execute(
                "update metirial set  name=?, qty=? where metirialId=?",
                metirialDTO.getName(),
                metirialDTO.getQty(),
                metirialDTO.getMetirialId()
        );
    }

    public boolean delete(String metirialId) throws SQLException {
        return SqlUtil.execute("delete from metirial where metirialId=?", metirialId);
    }

}

