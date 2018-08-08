package com.mamh.mybatis.demo;

import com.mamh.mybatis.demo.model.Classes;
import com.mamh.mybatis.demo.model.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class StudentTest {
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
    public void testGetClass() {
        String statement = "com.mamh.mybatis.demo.model.Student.getClass";
        Classes o = session.selectOne(statement, 2);
        System.out.println(o);
    }


}
