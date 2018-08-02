package com.atguigu.mvcapp.listener;

import com.atguigu.mvcapp.utils.FileUploadAppProperties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class FileUploadAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InputStream in = getClass().getClassLoader().getResourceAsStream("/upload.prop");

        Properties properties = new Properties();
        try {
            properties.load(in);
            for (Map.Entry<Object, Object> prop : properties.entrySet()) {
                FileUploadAppProperties.getInstance().addProperty(
                        (String) prop.getKey(),
                        (String) prop.getValue()
                );//add prop
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("com.atguigu.mvcapp.listener.FileUploadAppListener: "+ FileUploadAppProperties.getInstance().getProperties());


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
