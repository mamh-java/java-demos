package com.mamh.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
    private static HibernateUtils instance = new HibernateUtils();
    private static SessionFactory sessionFactory;

    private HibernateUtils() {

    }


    public static HibernateUtils getInstance() {
        return instance;
    }


    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            //1.创建一个SessionFactory 对象，创建session的工厂的一个类
            //1.1创建一个Configuration对象，对应hibernate的基本配置信息和对象关系映射信息
            Configuration configuration = new Configuration().configure();
            //1.2创建一个ServiceRegistry对象，hibernate4.x新添加的对象，hibernate任何的配置和服务都要在该对象中注册才能有效。
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).buildServiceRegistry();
            //1.3
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }


    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }


}
