package com.atguigu.mvcapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class HelloServletContextListener implements ServletContextListener,HttpSessionListener,ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("HelloServletContextListener contextInitialized创建" + servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("HelloServletContextListener contextDestroyed销毁");
    }






    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("HelloServletContextListener sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("HelloServletContextListener sessionDestroyed");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("HelloServletContextListener requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("HelloServletContextListener requestDestroyed");
    }
}
