package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.Car;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

        Car car1 = (Car) ctx.getBean("car1");
        System.out.println(car1);

        Car car2 = (Car) ctx.getBean("car2");
        System.out.println(car2);
    }
}
