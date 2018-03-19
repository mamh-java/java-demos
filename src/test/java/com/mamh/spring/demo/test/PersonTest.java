package com.mamh.spring.demo.test;

import com.mamh.spring.demo.beans.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PersonTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person = (Person) ctx.getBean("person");
        System.out.println(person);

        Person person1 = (Person) ctx.getBean("person1");
        System.out.println(person1);

        Person person2 = (Person) ctx.getBean("person2");
        System.out.println(person2);

        Person person3 = (Person) ctx.getBean("person3");
        System.out.println(person3);
    }
}
