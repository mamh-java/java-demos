package com.mamh.spring.demo.aop.byannotation;

import com.mamh.spring.demo.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;  //ioc容器字段创建这个对象,在xml中配置了这个bean节点的

    public Customer getCustomer(int id) {
        String sql = "SELECT hb_customer_id customerId, hb_customer_name customerName FROM hb_customer WHERE hb_customer_id  = ?";
        //使用sql中列别名和类的属性名映射，不支持级联映射

        //Customer customer = jdbcTemplate.queryForObject(sql, Customer.class, 0);
        BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class); //行映射器

        Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return customer;
    }
}
