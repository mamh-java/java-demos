package com.atguigu.mvcapp.dao;

import java.util.HashMap;
import java.util.Map;

public class CustomerDAOFactory {
    private static CustomerDAOFactory instance = new CustomerDAOFactory();

    private Map<String, CustomerDAO> daoMap = new HashMap<>();

    private String type;

    public void setType(String type) {
        this.type = type;
    }


    private CustomerDAOFactory() {
        daoMap.put("jdbc", new CustomerDAOJdbcImpl());
        daoMap.put("xml", new CustomerDAOXmlImpl());
    }


    public CustomerDAO getCustomerDAO() {
        return daoMap.get(type);
    }

    public static CustomerDAOFactory getInstance() {
        return instance;
    }
}
