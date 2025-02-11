package com.example.mywork.bo;

import com.example.mywork.bo.custom.impl.CustomerBOimpl;
import com.example.mywork.bo.custom.impl.EmployeeBOimpl;
import com.example.mywork.bo.custom.impl.MetirialBOimpl;
import com.example.mywork.bo.custom.impl.ProductBOimpl;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType{
        CUSTOMER,EMPLOYEE,METIRIAL,PRODUCT

    }
    public SuperBO getBO(BOType botype){
        switch (botype){
            case CUSTOMER:
                return new CustomerBOimpl();
            case EMPLOYEE:
                return new EmployeeBOimpl();
            case METIRIAL:
                return new MetirialBOimpl();
            case PRODUCT:
                return new ProductBOimpl();
            default:
                return null;
        }
    }
}
