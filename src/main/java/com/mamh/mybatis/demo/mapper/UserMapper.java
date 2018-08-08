package com.mamh.mybatis.demo.mapper;

import com.mamh.mybatis.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userMapper")
 public interface UserMapper {
    void save(User user);

    void update(User user);


    void delete(int id);

    User findById(int id);

    List<User> findAll();

}
