package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.Customer;

import java.util.List;

public class CustomerDAOXmlImpl implements CustomerDAO {
    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCumtomer cc) {
        System.out.println("get for list with criteria customer");
        return null;
    }

    @Override
    public List<Customer> getAll() {
        System.out.println("get all");
        return null;
    }

    @Override
    public Customer get(Integer id) {
        System.out.println("get");
        return null;
    }

    @Override
    public void save(Customer customer) {
        System.out.println("save");
    }

    @Override
    public void update(Customer customer) {
        System.out.println("update");
    }

    @Override
    public void delete(Integer id) {
        System.out.println("delete");
    }

    @Override
    public long getCountWithName(String name) {
        System.out.println("get count with name将来");
        return 0;
    }
}
