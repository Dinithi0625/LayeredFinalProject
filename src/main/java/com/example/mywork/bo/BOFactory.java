package com.example.mywork.bo;

import com.example.mywork.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType{
        CUSTOMER,EMPLOYEE,METIRIAL,PRODUCT,ORDERPRODUCT,ORDER

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
                case ORDERPRODUCT:
                    return new OrderDetailBOimpl();
                    case ORDER:
                        return new OrdersBOimpl();
            default:
                return null;
        }
    }
}
