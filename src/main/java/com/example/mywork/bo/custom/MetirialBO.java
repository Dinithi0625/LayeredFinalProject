package com.example.mywork.bo.custom;
import com.example.mywork.bo.SuperBO;
import com.example.mywork.entity.Metirial;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MetirialBO extends SuperBO {

    String getNextMetirialId() throws SQLException;

    boolean save(Metirial DTO) throws SQLException;

    ArrayList<Metirial> getAll() throws SQLException;

    boolean update(Metirial DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;

}
