package com.mamh.mybatis.demo;

import com.mamh.mybatis.demo.model.Classes;
import com.mamh.mybatis.demo.model.Student;
import com.mamh.mybatis.demo.model.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

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
        System.err.println(o);
    }

    @Test
    public void testGetTeacher() {
        String statement = "com.mamh.mybatis.demo.model.Student.getTeacher";
        Teacher o = session.selectOne(statement, 1);
        System.out.println(o);

        Teacher o1 = session.selectOne(statement, 2);
        System.out.println(o1);
    }

    @Test
    public void testGetStudents() {
        String statement = "com.mamh.mybatis.demo.model.Student.getStudents";
        List<Student> list = session.selectList(statement, 1);
        System.out.println(list);

    }

    @Test
    public void testGetClass2() {
        String statement = "com.mamh.mybatis.demo.model.Student.getClass2";
        Classes o = session.selectOne(statement, 2);
        System.err.println(o);
    }
}
