package com.mamh.hibernate.demo;


import com.mamh.hibernate.demo.entities.*;
import com.mysql.cj.jdbc.Clob;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.PortUnreachableException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


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
    public void testGetCategory(){
        Category category = (Category) session.get(Category.class, 1);
        System.out.println(category.getName());

        System.out.println(category.getItems().size());
    }

    @Test
    public void testSaveCategory() {
        Category category1 = new Category();
        category1.setName("c-aa");
        Category category2 = new Category();
        category2.setName("c-bb");

        Item item1 = new Item();
        item1.setName("item-aa");
        Item item2 = new Item();
        item2.setName("item-aa");

        category1.getItems().add(item1);
        category1.getItems().add(item2);
        category2.getItems().add(item1);
        category2.getItems().add(item2);

        session.save(category1);
        session.save(category2);
        session.save(item1);
        session.save(item2);
    }

    @Test
    public void testGetManager() {
        Manager manager = (Manager) session.get(Manager.class, 1);
        System.out.println(manager.getManagerName());
    }

    @Test
    public void testGetDepartment() {
        Department department = (Department) session.get(Department.class, 1);
        System.out.println(department.getDepartmentName());

    }

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setDepartmentName("dept-aaabb");

        Manager manager = new Manager();
        manager.setManagerName("mgr-aaabbb");

        manager.setDepartment(department);
        department.setManager(manager);

        //建议先保存没有外键的那个对象。这样会减少update语句。
//        session.save(manager);
        session.save(department);


    }

    @Test
    public void testCascade() {
        Customer customer = (Customer) session.get(Customer.class, 2);
        customer.getOrders().clear();
        session.delete(customer);
    }

    @Test
    public void testManyToOneDelete() {
        Customer customer = (Customer) session.get(Customer.class, 1);
        session.delete(customer);
    }

    @Test
    public void testManyToOneUpdate() {
        Order order = (Order) session.get(Order.class, 1);
        order.getCustomer().setCustomerName("new customer name!!!");
    }

    @Test
    public void testOneToManyUpdate() {
        Customer customer = (Customer) session.get(Customer.class, 1);
        customer.getOrders().iterator().next().setOrderName("d22");

    }

    @Test
    public void testOneToManyGet() {
        //对多的一端的集合使用延迟加载
        Customer customer = (Customer) session.get(Customer.class, 1);
        System.out.println(customer);
        //返回的集合类型是hibernate内置的一个类型，改类型具有延迟加载和存放代理对象功能
        System.out.println(customer.getOrders().getClass());

        session.close();

        System.out.println(customer.getOrders().size());
    }

    @Test
    public void testManyToOneGet() {
        Order order = (Order) session.get(Order.class, 1);
        System.out.println(order.getOrderName() + ":  " + order.getOrderId());

        Customer customer = order.getCustomer();
        System.out.println(customer);
    }

    @Test
    public void testManyToOneSave() {
        //双向关联关系
        Customer customer = new Customer("dfs");
        Order order1 = new Order();
        order1.setOrderName("order 1111111111");
        Order order2 = new Order();
        order2.setOrderName("order 2222222222");
        order1.setCustomer(customer);
        order2.setCustomer(customer);

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        //执行save操作
        //session.save(order1);
        //session.save(order2);
        //先保存customer，然后保存order。
        session.save(customer);

    }

    @Test
    public void testWorker() {
        Worker worker = new Worker();
        Pay pay = new Pay();
        pay.setMonthlyPay(12000);
        pay.setYearPay(170000);
        pay.setVacation(10);

        worker.setName("bright.ma");
        worker.setPay(pay);

        session.save(worker);
    }

    @Test
    public void testBlob() {
        News news = new News();
        news.setTitle("title");
        news.setAuthor("author");
        news.setDate(new java.util.Date());

        news.setDesc("desc.......");

        InputStream stream = null;
        try {
            stream = new FileInputStream("/home/mamh/马哥私房菜-8X8-02.jpg");
            Blob image = Hibernate.getLobCreator(session).createBlob(stream, stream.available());
            news.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        session.save(news);
    }

    @Test
    public void testPropUpdate() {
        News news = (News) session.get(News.class, 1);
        //news.setTitle("new title");

        System.out.println(news.getDesc());
    }

    @Test
    public void testDoWork() {
        session.doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                //在这个里面调用存储过程
                System.out.println(connection);
            }
        });
    }

    @Test
    public void testEvict() {
        News news1 = (News) session.get(News.class, 1);
        news1.setAuthor("11");

        News news2 = (News) session.get(News.class, 2);
        news2.setAuthor("==============");

//        session.evict(news1);
    }


    @Test
    public void testDelete() {
        //执行删除操作，只要OID和数据库表中的记录对应就会执行删除操作，如果没有对应的记录就抛出异常。
        //可以通过设置hibernate配置文件use_identifier_rollback属性为true，是删除对象后，把其OID置为null
        News news = new News(8, "ff", "fff", new Date(new java.util.Date().getTime()));
        //news.setId(10);
        System.out.println(news);
        session.delete(news);
        System.out.println(news);
    }

    @Test
    public void testSaveOrUpdate() {
        News news = new News("ff", "fff", new Date(new java.util.Date().getTime()));
        news.setId(23121);
        session.saveOrUpdate(news);
    }

    @Test
    public void testIdGenerator() {
        News news = new News("ff", "fff", new Date(new java.util.Date().getTime()));
        session.save(news);
    }

    @Test
    public void testUpdate() {
        //若更新一个持久化对象，不需要显示的调用update()方法,因为在调用transaction的commit方法时候，会先执行flush方法的。
        //更新一个游离对象，需要显示的调用session的update()方法，可以把游离对象变为持久化对象。
        //无论要更新的对象 是否和数据库表中的记录是否一致都会发送update语句的
        //如何让update盲目的发送update语句呢？在.hbm.xml 中设置select-before-update="true"（默认false），通常不设置为true，会影响效率。
        //若数据库表中没有对应的记录还继续调用update()方法，这时候会抛出异常。
        //同一个session中不能有ID相同的对象,会抛出异常NonUniqueObjectException
        News news = (News) session.get(News.class, 1);

        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        //这个时候news对象已经变成游离对象了，应为前面关闭了session，后续又重新打开了session。

        News news2 = (News) session.get(News.class, 1);
        news.setAuthor("sun");
        session.update(news);
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

    @Test
    public void testDynamicUpdate() {
        News news = (News) session.get(News.class, 1);
        news.setAuthor("dfsdfasdf");
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
