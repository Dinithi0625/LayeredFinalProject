package com.example.mywork.bo.custom;
import com.example.mywork.bo.SuperBO;
import com.example.mywork.dto.MetirialDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MetirialBO extends SuperBO {

    String getNextMetirialId() throws SQLException;

    boolean save(MetirialDTO DTO) throws SQLException;

    ArrayList<MetirialDTO> getAll() throws SQLException;

    boolean update(MetirialDTO DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;

}
