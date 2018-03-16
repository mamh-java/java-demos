package com.mamh.spring.demo.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class HelloWorld {
    private String name;

    public HelloWorld() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("hello: = " + getName());
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");

        helloWorld.hello();

        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

    }
}
