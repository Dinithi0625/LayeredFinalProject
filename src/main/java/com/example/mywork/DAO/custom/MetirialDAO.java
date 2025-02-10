package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.dto.MetirialDTO;

import java.sql.SQLException;

public interface MetirialDAO extends CrudDAO<MetirialDTO> {

    String getNextMetirialId() throws SQLException;

}
