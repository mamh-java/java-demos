package com.atguigu.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "forwardDestServlet",urlPatterns = {"/forwardDestServlet"})
public class ForwardDestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("forward dest servlet do get ....");

        System.out.println("ForwardDestServlet servlet do get ...." + request.getAttribute("r"));

        
        PrintWriter out = response.getWriter();
        out.println("dest page.....");

    }
}
