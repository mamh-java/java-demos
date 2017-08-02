package com.atguigu.javaweb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "forwardServlet", urlPatterns = {"/forwardServlet"})
public class ForwardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("forward servlet do get ....");

        //转发这里的属性可以传递给下个servlet，应为请求转发是一个request
        request.setAttribute("r", "forward servlet");
        System.out.println("forward servlet do get ...." + request.getAttribute("r"));


        //请求的转发
        //1.调用HttpServeltRequest 的 getRequestDispatcher(addr) 方法，传入要转发的地址
        String path = "forwardDestServlet";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);

        //2.调用HttpServeltRequest 的 forward() 进行请求的转发
        requestDispatcher.forward(request, response); //这样就转发到forwardDestServlet这里了


        //请求转发和重定向的区别
        
        //请求转发地址栏不发生变化.
        // 转发是一个请求,
        // 请求转发只能发到当前web应用的某个资源,不能转发出去.
        // 对转发而言斜杠代表的是当前应用的根目录.


        //重定向会改变的.
        // 重定向是２个请求.
        // 重定向可以到任何资源.
        // 对于重定向斜杠代表是站点的/


        //在这个基础上来讲mvc 设计模式



    }
}
