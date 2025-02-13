package com.example.mywork.dao.custom;

import com.example.mywork.dao.CrudDAO;
import com.example.mywork.entity.Metirial;

import java.sql.SQLException;

public interface MetirialDAO extends CrudDAO<Metirial> {

    String getNextMetirialId() throws SQLException;

}
