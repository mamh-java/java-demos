package com.mamh.spring.demo.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 模拟service层
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void add() {
        System.out.println("UserService add()....");
        userRepository.save();
    }


    @Override
    public String toString() {
        return "UserService{}";
    }
}
