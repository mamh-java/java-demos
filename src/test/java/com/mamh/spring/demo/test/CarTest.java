package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.Car;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car7 = (Car) ctx.getBean("car7");
        System.out.println(car7);


    }
}
