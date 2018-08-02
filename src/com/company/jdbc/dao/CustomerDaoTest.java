package com.company.jdbc.dao;

import com.company.jdbc.Customer;
import com.company.jdbc.JdbcTools;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class CustomerDaoTest {
    @Test
    public void testBatch() {
        fail("not ye implemented");
    }


    @Test
    public void testGet() throws Exception {
        Connection connection = null;
        try {
            connection = JdbcTools.getConnection();
            String sql = "select id,name, address, phone from customers where id = ?";
            CustomerDao customerDao = new CustomerDao();
            Customer customer = customerDao.get(connection, sql, 16);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
