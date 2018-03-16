package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.HelloWorld;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");

        helloWorld.hello();
    }
}
