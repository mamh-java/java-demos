package com.mamh.spring.demo.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringServletListener implements ServletContextListener {
    /**
     * # 如何创建ioc容器呢？？？？
     * # 在非web应用中。我们直接new出来的一个ioc容器
     * # 在web应用中，要在web应用被服务器加载时就创建ioc容器。使用监听器。ServletContextListener.
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");
        ServletContext servletContext = servletContextEvent.getServletContext();
        String configxml = servletContext.getInitParameter("applicationContext");


        //这个xml文件应该是可配置的,配置到web应用中初始化参数中
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configxml);

        servletContext.setAttribute("applicationContext", ctx);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
