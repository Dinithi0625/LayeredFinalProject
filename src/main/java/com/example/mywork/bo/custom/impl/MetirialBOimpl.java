package com.example.mywork.bo.custom.impl;

import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.MetirialDAO;
import com.example.mywork.bo.custom.MetirialBO;
import com.example.mywork.dto.MetirialDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MetirialBOimpl implements MetirialBO {
    MetirialDAO metirialDAO = (MetirialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.METIRIAL);

    @Override
    public String getNextMetirialId() throws SQLException {
        return metirialDAO.getNextMetirialId();
    }

    @Override
    public boolean save(MetirialDTO DTO) throws SQLException {
        return metirialDAO.save(DTO);
    }

    @Override
    public ArrayList<MetirialDTO> getAll() throws SQLException {
        return metirialDAO.getAll();
    }

    @Override
    public boolean update(MetirialDTO DTO) throws SQLException {
        return metirialDAO.update(DTO);
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return metirialDAO.delete(Id);
    }

}
