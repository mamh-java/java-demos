package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.Address;
import com.mamh.spring.demo.beans.Car;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Address address = (Address) ctx.getBean("address");
        System.out.println(address);

    }
}
