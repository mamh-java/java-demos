package com.mamh.hibernate.demo;


import com.mamh.hibernate.hql.entities.Department;
import com.mamh.hibernate.hql.entities.Employee;
import oracle.jdbc.driver.DatabaseError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.util.Iterator;
import java.util.List;


public class HibernateCacheTest {
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
        //5.提交事务
        transaction.commit();

        //6.关闭session对象
        session.close();

        //7.关闭SessionFactory 对象
        sessionFactory.close();

    }


    @Test
    public void testHibernateCache() {
        //hibernate 二级缓存
        Employee employee = (Employee) session.get(Employee.class, 100);
        System.out.println(employee.getName());

        Employee employee1 = (Employee) session.get(Employee.class, 100);
        System.out.println(employee1.getName());


    }

    @Test
    public void testHibernateCache1() {
        //hibernate 二级缓存
        Employee employee = (Employee) session.get(Employee.class, 100);
        System.out.println(employee.getName());
        //5.提交事务
        transaction.commit();

        //6.关闭session对象
        session.close();

        //再次重新打开一个session
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Employee employee1 = (Employee) session.get(Employee.class, 100);
        System.out.println(employee1.getName());

    }


    @Test
    public void testHibernateCache2() {
        Department department1 = (Department) session.get(Department.class, 80);
        System.out.println("deapatment1 = " + department1.getName());
        System.out.println("deapatment1 = " + department1.getEmps().size());

        //5.提交事务
        transaction.commit();

        //6.关闭session对象
        session.close();

        //再次重新打开一个session
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Department department2 = (Department) session.get(Department.class, 80);
        System.out.println("deapatment2 = " + department2.getName());
        System.out.println("deapatment2 = " + department2.getEmps().size());
    }

    @Test
    public void testQueryCache() {
        Query query = session.createQuery("from Employee ");
        query.setCacheable(true);
        List list = query.list();
        System.out.println(list.size());


        List list1 = query.list();
        System.out.println(list1.size());

    }

    @Test
    public void testTimeStampCache(){
        Query query = session.createQuery("from Employee ");
        query.setCacheable(true);
        List list = query.list();
        System.out.println(list.size());
        System.out.println("\n");

        //在两次查询之间 对 employee 更新
        Employee employee = (Employee) session.get(Employee.class, 100);
        employee.setSalary(30f);

        System.out.println("\n");
        List list1 = query.list();
        System.out.println(list1.size());

    }

    @Test
    public void testIterateCache(){
        Department department = (Department) session.get(Department.class, 70);
        System.out.println(department.getName());
        System.out.println(department.getEmps().size());

        Query query = session.createQuery("from Employee e where e.dept.id = 80");
        //List<Employee> list = query.list();
        //System.out.println(list.size());

        Iterator<Employee> iterable = query.iterate();
        for (Iterator<Employee> it = iterable; it.hasNext(); ) {
            Employee employee = it.next();
            System.out.println(employee.getName());

        }

    }
}
