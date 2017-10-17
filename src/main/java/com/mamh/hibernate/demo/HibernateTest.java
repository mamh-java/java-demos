package com.mamh.hibernate.demo;


import com.mamh.hibernate.demo.entities.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.sql.Date;


public class HibernateTest {
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

    @Test
    public void testLoad() {
        //load和get的区别：
        //get会立即加载对象，load方法若不用该对象则不会立即查询，而返回一个代理对象。
        //get立即检索，load是延迟检索。
        //若数据表中没有对应的记录,session也没有关闭,同时要使用该对象时候：get返回null，load抛出异常。
        //load方法可能会抛出懒加载异常：
        // org.hibernate.LazyInitializationException: could not initialize proxy - no Session
        // 在需要初始化代理对象之前关闭了session就会抛出这个异常。
        News news1 = (News) session.load(News.class, 10);
        //session.close();
        System.out.println(news1);
    }

    @Test
    public void testGet() {
        News news1 = (News) session.get(News.class, 1);
        System.out.println(news1);
    }

    @Test
    public void testPersist() {
        //和save()方法很类似,区别：
        //在调用persist方法之前，调用了setId(),对象已经有了ID了，则不会执行insert操作，会抛出异常
        News news = new News();
        news.setAuthor("mm");
        news.setTitle("ssssssssss");
        news.setDate(new Date(new java.util.Date().getTime()));
        news.setId(234234);
        session.persist(news);
    }

    @Test
    public void testSave() {
        //把临时对象变为持久化对象
        //为对象分配ID,在save()方法之前设置ID是无效的，save()之后也是不能改这个ID的
        //持久化的对象的ID是不能进行修改的
        //在flush缓存时候会发送一条insert语句
        //把临时对象保存到数据库中
        News news = new News();
        news.setAuthor("mm");
        news.setTitle("ssssssssss");
        news.setDate(new Date(new java.util.Date().getTime()));

        session.save(news);
    }


    @Test
    public void testClear() {
        News news0 = (News) session.get(News.class, 1);
        session.clear();
        News news1 = (News) session.get(News.class, 1);
    }

    @Test
    public void testRefresh() {
        News news = (News) session.get(News.class, 1);
        System.out.println(news);
        session.refresh(news);
        System.out.println(news);
    }


    @Test
    public void testSessionFlush() {
        News news = (News) session.get(News.class, 1);
        System.out.println(news);
        news.setAuthor("mamha");


        session.save(news);
    }

    @Test
    public void testSessionCache() {

    }

    @Test
    public void testSession() {
        //4.执行保存操作
        //News news = new News("title:java", "author: mamh", new Date(new java.util.Date().getTime()));
        //session.save(news);

        Object news1 = session.get(News.class, 1);
        System.out.println(news1);

        Object news2 = session.get(News.class, 1);
        System.out.println(news2);

        System.out.println(news1 == news2);


        //和session缓存相关的方法
        session.flush();//让数据库和session缓存中的对象保持一致。


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
    public void test() {
        //1.创建一个SessionFactory 对象，创建session的工厂的一个类
        SessionFactory sessionFactory = null;
        //1.1创建一个Configuration对象，对应hibernate的基本配置信息和对象关系映射信息
        Configuration configuration = new Configuration().configure();
        //1.2创建一个ServiceRegistry对象，hibernate4.x新添加的对象，hibernate任何的配置和服务都要在该对象中注册才能有效。
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        //1.3
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        //2.创建一个Session对象,这个和jdbc中的connection很类似
        Session session = sessionFactory.openSession();


        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.执行保存操作
        News news = new News("title:java", "author: mamh", new Date(new java.util.Date().getTime()));
        session.save(news);

        Object o = session.get(News.class, 1);
        System.out.println(o);


        //5.提交事务
        transaction.commit();


        //6.关闭session对象
        session.close();

        //7.关闭SessionFactory 对象
        sessionFactory.close();

    }
}
