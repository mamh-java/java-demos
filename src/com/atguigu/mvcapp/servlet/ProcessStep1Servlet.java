package com.atguigu.mvcapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProcessStep1Servlet", urlPatterns = {"/shopping/processStep1"})
public class ProcessStep1Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] books = request.getParameterValues("book");

        request.getSession().setAttribute("books", books);

        // 请求转发时候 斜杠 / 代表web应用的跟路径
        // web.xml 中配置url-parttern时候代表web应用的根路径
        // 各种标签中的斜杠
        // 一句话总结，交个web容器处理的斜杠代表web应用的根路径
        System.out.println(request.getContextPath()+"====");
        response.sendRedirect(request.getContextPath() + "/shopping/step2.jsp");


        // 超链接 <a href="/xxx.jsp"></a> 这里斜杠/代表web站点根路径
        // 表单中的斜杠
        // response.sendRedirect() 中的斜杠代表web站点的根路径
        // 一句话总结，交给浏览器处理的斜杠都是代表web站点根路径
    }


}
