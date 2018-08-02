package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.Customer;

public class CriteriaCumtomer extends Customer {
    public CriteriaCumtomer(String name, String address, String phone) {
        super(name, address, phone);
    }

    @Override
    public String getName() {
        return super.getName() == null ? "%%" : "%" + super.getName() + "%";
    }

    @Override
    public String getAddress() {
        return super.getAddress() == null ? "%%" : "%" + super.getAddress() + "%";
    }

    @Override
    public String getPhone() {
        return super.getPhone() == null ? "%%" : "%" + super.getPhone() + "%";
    }
}
