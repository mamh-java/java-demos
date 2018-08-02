package com.mamh.mybatis.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class UserTest {
    private SqlSession session;

    @Before
    public void before() {
        String resource = "mybatis-config.xml";
        InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sqlSessionFactory.openSession();
        //session = sqlSessionFactory.openSession(true);自动提交auto commit
    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void testDeleteUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".delUser";
        int delete = session.delete(statement, 3);
        Assert.assertEquals(1, delete);

        delete = session.delete(statement, 3);
        Assert.assertEquals(0, delete);
    }

    @Test
    public void testUpdateUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".updateUser";
        User user = new User(3, "mamh", 20);
        int update = session.update(statement, user);
        Assert.assertEquals(1, update);
    }

    @Test
    public void testAddUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".addUser";
        User user = new User("bright", 28);
        int insert = session.insert(statement, user);
        Assert.assertEquals(insert, 1);
    }

    @Test
    public void testGetUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".getUser";
        User user1 = session.selectOne(statement, 1);
        Assert.assertEquals("tom", user1.getName());

        User user2 = session.selectOne(statement, 2);

        Assert.assertEquals("jack", user2.getName());
    }

    @Test
    public void testGetAllUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".getAllUser";
        List<User> user = session.selectList(statement);

        Assert.assertEquals(3, user.size());
    }

    @Test
    public void test() {
        String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlSessionFactory.openSession();

            String statement = "com.mamh.mybatis.demo.User" + ".getUser";
            User user = session.selectOne(statement, 2);


            session.commit();

            Assert.assertEquals("jack", user.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
