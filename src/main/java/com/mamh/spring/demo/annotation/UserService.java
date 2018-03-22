package com.mamh.spring.demo.annotation;


import org.springframework.stereotype.Service;

/**
 * 模拟service层
 */
@Service
public class UserService {


    public void add() {
        System.out.println("UserService add()....");
    }


    @Override
    public String toString() {
        return "UserService{}";
    }
}
