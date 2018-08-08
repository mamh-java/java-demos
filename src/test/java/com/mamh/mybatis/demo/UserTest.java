package com.mamh.mybatis.demo;

import com.mamh.mybatis.demo.mapper.UserMapper;
import com.mamh.mybatis.demo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class UserTest {


    private BeanFactory context;

    @Before
    public void before() {

        context = new ClassPathXmlApplicationContext("spring.xml");

    }

    @Test
    public void test() {
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
        System.out.println(userMapper.findAll());

    }


}
