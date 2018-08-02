package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getForListWithCriteriaCustomer(CriteriaCumtomer cc);

    List<Customer> getAll();

    Customer get(Integer id);

    void save(Customer customer);

    void update(Customer customer);

    void delete(Integer id);

    //查询这个name记录有多少个
    long getCountWithName(String name);


}
