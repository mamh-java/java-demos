package com.atguigu.mvcapp.servlet;

import com.atguigu.mvcapp.dao.PermissionDAO;
import com.atguigu.mvcapp.domain.Authority;
import com.atguigu.mvcapp.domain.Permission;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "authorityServlet", urlPatterns = {"/authorityServlet"})
public class AuthorityServlet extends HttpServlet {
    private PermissionDAO dao = new PermissionDAO();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        String methodName = req.getParameter("method");
        System.out.println("doPost: methodName: " + methodName);
        Method method = null;
        try {
            method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public void getAuthorities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        Permission permission = dao.get(username);
        System.out.println("getAuthorities: " + permission);


        ArrayList<Authority> authorities = dao.getAuthorities();
        System.out.println("getAuthorities: " + authorities);

        request.setAttribute("user", permission);
        request.setAttribute("authorities", authorities);


        request.getRequestDispatcher(request.getContextPath() + "/permission/authority-manager.jsp").forward(request, response);

    }

    public void updateAuthorities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String[] urls = request.getParameterValues("authority");//这里获得的是url

        List<Authority> newauthorities = dao.getAuthorities(urls);

        dao.update(username, newauthorities);

        response.sendRedirect(request.getContextPath() + "/permission/authority-manager.jsp");

        System.out.println("updateAuthorities: " + dao.get(username));
    }
}
