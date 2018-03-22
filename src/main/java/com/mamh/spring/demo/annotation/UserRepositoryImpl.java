package com.mamh.spring.demo.annotation;


import org.springframework.stereotype.Repository;

/**
 * 模拟持久化层
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
    public void save() {
        System.out.println("UserRepositoryImpl save()....");
    }


    @Override
    public String toString() {
        return "UserRepositoryImpl{}";
    }
}
