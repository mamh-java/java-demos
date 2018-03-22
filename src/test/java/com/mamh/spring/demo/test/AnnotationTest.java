package com.mamh.spring.demo.test;

import com.mamh.spring.demo.annotation.TestObject;
import com.mamh.spring.demo.annotation.UserController;
import com.mamh.spring.demo.annotation.UserRepository;
import com.mamh.spring.demo.annotation.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        TestObject testObject = (TestObject) ctx.getBean("testObject");
        System.out.println(testObject);

        UserController userController = (UserController) ctx.getBean("userController");
        System.out.println(userController);

        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
        System.out.println(userRepository);

        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService);
    }
}
