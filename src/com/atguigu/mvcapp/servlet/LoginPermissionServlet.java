package com.atguigu.mvcapp.servlet;

import com.atguigu.mvcapp.dao.PermissionDAO;
import com.atguigu.mvcapp.domain.Permission;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@WebServlet(name = "LoginPermissionServlet", urlPatterns = {"/loginPermissionServlet"})
public class LoginPermissionServlet extends HttpServlet {
    private PermissionDAO dao = new PermissionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("method");
        System.out.println("doPost: methodName: " + methodName);
        Method method = null;
        try {
            method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }//end doPost()


    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 获取name
        String name = request.getParameter("username");

        //2.获取dao获取用户信息，把用户信息放到session中
        Permission permisson = dao.get(name);
        request.getSession().setAttribute("user", permisson);
        System.out.println("permission = " + permisson);


        //3.重定向到 articles.jsp
        response.sendRedirect("/permission/articles.jsp");

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();

        response.sendRedirect("/login.jsp");
    }

}
