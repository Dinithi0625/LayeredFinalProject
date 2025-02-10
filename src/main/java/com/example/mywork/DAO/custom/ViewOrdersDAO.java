package com.example.mywork.DAO.custom;
import com.example.mywork.dto.tm.OrderProductTM;
import java.util.List;

public interface ViewOrdersDAO {

    public List<OrderProductTM> getOrderData();

}
