package com.example.mywork.dao;
import com.example.mywork.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
       return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType{
        CUSTOMER,EMPLOYEE,METIRIAL,PRODUCT,ORDERPRODUCT,ORDERS

    }
    public  CrudDAO getDAO(DAOType daoFactory){
        switch (daoFactory){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case METIRIAL:
                return new MetirialDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case ORDERPRODUCT:
                return new OrderDetailDAOImpl();
                case ORDERS:
                    return new OrdersDAOImpl();
            default:
                return null;
        }
    }
}
