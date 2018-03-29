package com.mamh.spring.demo.test;

import com.mamh.spring.demo.dao.BookShopDao;
import com.mamh.spring.demo.dao.BookShopService;
import com.mamh.spring.demo.dao.Cashier;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateTest {
    private ApplicationContext ctx;
    private DataSource datasource;
    private BookShopDao bookShopDao;
    private BookShopService bookShopService;
    private Cashier cashier;


    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        datasource = ctx.getBean(DataSource.class);
        bookShopDao = ctx.getBean(BookShopDao.class);

        bookShopService = ctx.getBean(BookShopService.class);
        cashier = ctx.getBean(Cashier.class);
    }

    @Test
    public void test() {
        try {
            Connection connection = datasource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {


        }

    }

    @Test
    public void test1() {
        bookShopService.purchase("mamh", "1001");
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<String>();
        list.add("1001");
        list.add("1002");
        list.add("1003");
        cashier.checkout("mamh", list);
    }
}
