package com.atguigu.mvcapp.servlet;

import com.atguigu.mvcapp.dao.CustomerDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/switch.prop");

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String type = properties.getProperty("type");
            CustomerDAOFactory.getInstance().setType(type);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
