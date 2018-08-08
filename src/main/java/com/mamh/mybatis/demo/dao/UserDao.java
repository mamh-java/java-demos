package com.mamh.mybatis.demo.dao;

import com.mamh.mybatis.demo.mapper.UserMapper;
import com.mamh.mybatis.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.findAll();
    }
}
