package com.mamh.spring.demo.servlet;

import com.mamh.spring.demo.beans.HelloWorld;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");

        HelloWorld helloworld = (HelloWorld) applicationContext.getBean("helloworld");
        helloworld.hello();


    }
}
