package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.entity.Metirial;
import java.sql.SQLException;

public interface MetirialDAO extends CrudDAO<Metirial> {

    String getNextMetirialId() throws SQLException;

}
