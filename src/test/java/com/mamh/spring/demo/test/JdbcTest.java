package com.mamh.spring.demo.test;

import com.mamh.spring.demo.aop.byannotation.CustomerDao;
import com.mamh.spring.demo.beans.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcTest {

    private ClassPathXmlApplicationContext ctx;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        dataSource = (DataSource) ctx.getBean("dataSource");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

        namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");

    }

    @Test
    public void testCustomerDao() {
        CustomerDao customerDao = ctx.getBean(CustomerDao.class);
        Customer customer = customerDao.getCustomer(2);

        System.out.println(customer);
    }

    @Test
    public void update() {
        String sql = "UPDATE hb_customer SET hb_customer_name=? WHERE hb_customer_id = ?";

        jdbcTemplate.update(sql, "jack", 1);  //更新数据

    }

    @Test
    public void update1() {
        //批量更新操作
        //批量的insert，update，delete都可以
        String sql = "INSERT INTO hb_customer(hb_customer_id, hb_customer_name) VALUES(?, ?)";
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{12, "12121212"});
        list.add(new Object[]{13, "xxxxxxxxx"});
        list.add(new Object[]{14, "sssssssss"});
        list.add(new Object[]{15, "eeeeee"});
        list.add(new Object[]{16, "wwwwwww"});

        jdbcTemplate.batchUpdate(sql, list);
    }

    @Test
    public void update2() {
        String sql = "INSERT INTO hb_customer(hb_customer_id, hb_customer_name) VALUES(:id, :name)";

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", 111);
        parameter.put("name", "namesxxxxxx");
        //这里可以使用map来传入参数，当参数多了，不用一个一个取对位置了。

        namedParameterJdbcTemplate.update(sql, parameter);

    }

    @Test
    public void update3() {
        String sql = "INSERT INTO hb_customer(hb_customer_id, hb_customer_name) VALUES(:customerId, :customerName)";

        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("alsdjfaldfjaldkj");
        //使用具名参数时，可以传入一个对象进行更新操作。若有多个参数，不用取一个一个对位置了。

        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(customer);

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);

    }


    @Test
    public void query() {
        String sql = "SELECT hb_customer_id customerId, hb_customer_name customerName FROM hb_customer WHERE hb_customer_id  = ?";
        //使用sql中列别名和类的属性名映射，不支持级联映射


        //Customer customer = jdbcTemplate.queryForObject(sql, Customer.class, 0);
        BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class); //行映射器

        Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, 1);

        System.out.println(customer);

        Customer customer2 = jdbcTemplate.queryForObject(sql, rowMapper, 2);

        System.out.println(customer2);
    }

    @Test
    public void query1() {
        String sql = "SELECT hb_customer_id customerId, hb_customer_name customerName FROM hb_customer WHERE hb_customer_id  >= ?";
        //使用sql中列别名和类的属性名映射，不支持级联映射


        //Customer customer = jdbcTemplate.queryForObject(sql, Customer.class, 0);
        BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<Customer>(Customer.class); //行映射器

        List<Customer> customer = jdbcTemplate.query(sql, rowMapper, 1);

        System.out.println(customer);


    }


    @Test
    public void query2() {
        String sql = "SELECT count(hb_customer_id) FROM hb_customer";
        //使用sql中列别名和类的属性名映射，不支持级联映射,获取单个值，统计查询。

        Long aLong = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(aLong);


    }


}
