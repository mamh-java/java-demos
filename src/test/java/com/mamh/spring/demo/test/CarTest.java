package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.Car;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car1 = (Car) ctx.getBean("car5");
        System.out.println(car1);

        Car car5 = (Car) ctx.getBean("car5");
        System.out.println(car5);

        System.out.println(car1 == car5);


        ComboPooledDataSource dataSource = (ComboPooledDataSource) ctx.getBean("dataSource1");
        System.out.println(dataSource);
        System.out.println(dataSource.getPassword());
    }
}
