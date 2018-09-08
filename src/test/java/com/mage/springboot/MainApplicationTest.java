package com.mage.springboot;

import com.mage.springboot.bean.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTest {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

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

}