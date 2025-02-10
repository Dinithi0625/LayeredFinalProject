package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.dto.CustomerDTO;

import javax.xml.stream.events.DTD;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {
    String getNextCustomerId() throws SQLException;

}
