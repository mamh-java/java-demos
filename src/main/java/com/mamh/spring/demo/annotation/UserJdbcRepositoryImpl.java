package com.mamh.spring.demo.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 模拟持久化层
 */
@Repository("userRepository1")
public class UserJdbcRepositoryImpl implements UserRepository {

    @Autowired(required = false)
    private TestObject testObject;

    public void save() {
        System.out.println("UserJdbcRepositoryImpl save()....");
        System.out.println(testObject);
    }


    @Override
    public String toString() {
        return "UserJdbcRepositoryImpl{}";
    }
}
