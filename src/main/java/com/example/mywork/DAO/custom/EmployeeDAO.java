package com.example.mywork.DAO.custom;
import com.example.mywork.DAO.CrudDAO;
import com.example.mywork.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<EmployeeDTO> {

    String getNextEmployeeId() throws SQLException;

}
