package com.example.mywork.bo.custom.impl;

import com.example.mywork.bo.custom.MetirialBO;
import com.example.mywork.dao.DAOFactory;
import com.example.mywork.dao.custom.MetirialDAO;
import com.example.mywork.dto.MetirialDTO;
import com.example.mywork.entity.Metirial;

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
        return metirialDAO.save(new Metirial(DTO.getMetirialId(),DTO.getName(),DTO.getQty()));
    }

    @Override
    public ArrayList<MetirialDTO> getAll() throws SQLException {
        ArrayList<Metirial> metirials = metirialDAO.getAll();
        ArrayList<MetirialDTO> metirialDTOS = new ArrayList<>();
        for (Metirial metirial : metirials) {
            metirialDTOS.add(new MetirialDTO(metirial.getMetirialId(),metirial.getName(),metirial.getQty()));
        }
        return metirialDTOS;
    }

    @Override
    public boolean update(MetirialDTO DTO) throws SQLException {
        return metirialDAO.update(new Metirial(DTO.getMetirialId(),DTO.getName(),DTO.getQty()));
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return metirialDAO.delete(Id);
    }
}
