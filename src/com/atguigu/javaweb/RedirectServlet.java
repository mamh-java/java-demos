package com.atguigu.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "redirectServlet",urlPatterns = {"/redirectServlet"})
public class RedirectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("redirect servlet doget()...");

        request.setAttribute("r", "redirect servlet");
        System.out.println("redirect servlet do get ...." + request.getAttribute("r"));

        String location = "forwardDestServlet";
        //重定向
        response.sendRedirect(location);

    }
}
