package com.mamh.mybatis.demo.service;

import com.mamh.mybatis.demo.dao.UserDao;
import com.mamh.mybatis.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }
}
