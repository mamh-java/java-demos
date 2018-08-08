package com.mamh.mybatis.demo;

import com.mamh.mybatis.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class UserTest {


    private BeanFactory context;

    @Before
    public void before() {

        context = new ClassPathXmlApplicationContext("spring.xml");

    }

    @Test
    public void test() {
        UserService service = (UserService) context.getBean(UserService.class);
        System.out.println(service.getAll());

    }


}
