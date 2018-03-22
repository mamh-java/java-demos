package com.mamh.spring.demo.annotation;


import org.springframework.stereotype.Controller;

/**
 * 模拟表现层
 */
@Controller
public class UserController {

    public void execute() {
        System.out.println("UserController execute()....");//模拟表现层的
    }

    @Override
    public String toString() {
        return "UserController{}";
    }
}
