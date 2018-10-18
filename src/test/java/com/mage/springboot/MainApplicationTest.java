package com.mage.springboot;

import com.mage.springboot.bean.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTest {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Autowired
    DataSource dataSource;

    @Test
    public void test1(){
        boolean b = ioc.containsBean("student");
        System.err.println(b);
        System.err.println(ioc.getBean("student"));
    }


    @Test
    public void test() {
        System.err.println(person);
    }

    @Test
    public void test2() throws SQLException {
        System.err.println(dataSource.getClass());
        System.err.println(dataSource.getConnection());
    }
}