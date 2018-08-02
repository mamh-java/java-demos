package com.mamh.mybatis.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class UserTest {
    private String resource = "mybatis-config.xml";
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    @Before
    public void before() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sqlSessionFactory.openSession();

    }

    @After
    public void after() {
        session.commit();
        session.close();
    }

    @Test
    public void testGetUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".getUser";
        User user1 = session.selectOne(statement, 1);

        System.out.println(user1);

        User user2 = session.selectOne(statement, 2);

        System.out.println(user2);
    }

    @Test
    public void testGetAllUser() {
        String statement = "com.mamh.mybatis.demo.User" + ".getAllUser";
        List<User> user = session.selectList(statement);

        System.out.println(user);
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

            System.out.println(user.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
