package com.mamh.hibernate.demo;


import com.mamh.hibernate.hql.entities.Department;
import com.mamh.hibernate.hql.entities.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;


public class HibernateQbcTest {
    SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;


    @Before
    public void init() {
        System.out.println("=init=");
        //1.创建一个SessionFactory 对象，创建session的工厂的一个类
        //1.1创建一个Configuration对象，对应hibernate的基本配置信息和对象关系映射信息
        Configuration configuration = new Configuration().configure();
        //1.2创建一个ServiceRegistry对象，hibernate4.x新添加的对象，hibernate任何的配置和服务都要在该对象中注册才能有效。
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        //1.3
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //2.创建一个Session对象,这个和jdbc中的connection很类似
        session = sessionFactory.openSession();
        //3.开启事务
        transaction = session.beginTransaction();
    }


    @After
    public void destroy() {
        System.out.println("=destroy=");
        //5.提交事务
        transaction.commit();

        //6.关闭session对象
        session.close();

        //7.关闭SessionFactory 对象
        sessionFactory.close();

    }


    @Test
    public void testQBC() {
        //1.创建一个criteria 对象
        Criteria criteria = session.createCriteria(Employee.class);
        //2. 添加查询条件，在qbc中查询条件使用criterion来表示
        //criterion可以通过Restrictions类的静态方法得到
        criteria.add(Restrictions.eq("email", "SKUMAR"));
        criteria.add(Restrictions.gt("salary", 5000f));

        Employee employee = (Employee) criteria.uniqueResult();

        System.out.println(employee);


    }


    @Test
    public void testQBC2() {
        //1.创建一个criteria 对象
        Criteria criteria = session.createCriteria(Employee.class);

        //and  的使用  Conjunction 本身就是一个criterion对象，其中还可以添加criterion对象
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.like("name", "a", MatchMode.ANYWHERE));

        Department dept = new Department();
        dept.setId(20);
        conjunction.add(Restrictions.eq("dept", dept));
        System.out.println(conjunction);


        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.ge("salary", 6000f));
        disjunction.add(Restrictions.isNull("email"));

        criteria.add(disjunction).add(conjunction);

        List list = criteria.list();
        System.out.println(list);


    }

    @Test
    public void testQBC3() {
        //1.创建一个criteria 对象
        Criteria criteria = session.createCriteria(Employee.class);

        //统计查询
        criteria.setProjection(Projections.max("salary"));

        Float o = (Float) criteria.uniqueResult();
        System.out.println(o);


    }


    @Test
    public void testQBC4() {
        //1.创建一个criteria 对象
        Criteria criteria = session.createCriteria(Employee.class);

        //排序
        criteria.addOrder(Order.asc("salary"));
        criteria.addOrder(Order.desc("email"));

        int pageSize = 5;
        int pageNo = 3;

        criteria.setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize);
        List list = criteria.list();
        System.out.println(list);

    }


    @Test
    public void testNativeSql() {
        String sql = "INSERT INTO HB_DEPARTMENT VALUES(?, ?)";
        Query sqlQuery = session.createSQLQuery(sql);

        sqlQuery.setInteger(0, 280).setString(1, "马哥私房菜").executeUpdate();

    }

    @Test
    public void testHQLUpdate(){
        String sql = "delete from Department  d where d.id = :id";
        Query query = session.createQuery(sql);
        query.setInteger("id", 280).executeUpdate();
    }
}
