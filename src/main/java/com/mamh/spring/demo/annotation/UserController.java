package com.mamh.spring.demo.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 模拟表现层
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void execute() {
        System.out.println("UserController execute()....");//模拟表现层的
        userService.add();
    }

    @Override
    public String toString() {
        return "UserController{}";
    }
}
