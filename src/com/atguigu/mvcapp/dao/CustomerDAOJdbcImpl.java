package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.Customer;

import java.util.List;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO {


    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCumtomer cc) {
        String sql = "SELECT id, name, address, phone FROM customers where name like ? AND address like ?  AND phone like ?";
        return getForList(sql, cc.getName(), cc.getAddress(), cc.getPhone());
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT id, name, address, phone FROM customers";
        return getForList(sql);
    }


    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers(name, address, phone) VALUES(?, ?, ?)";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customers set name = ? , address = ?, phone = ? where id = ?";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone(), customer.getId());
    }


    @Override
    public Customer get(Integer id) {
        String sql = "SELECT id,name, address, phone FROM customers WHERE id = ?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        update(sql, id);
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "SELECT count(id) FROM customers WHERE name = ?";
        return getForValue(sql, name);
    }
}
