package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.Car;
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
    }
}
