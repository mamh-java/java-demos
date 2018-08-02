package com.atguigu.mvcapp.servlet;

import com.atguigu.mvcapp.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProcessStep2Servlet", urlPatterns = {"/processStep2"})
public class ProcessStep2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String cardType = request.getParameter("cardtype");
        String cardId = request.getParameter("cardid");

        Customer customer = new Customer(name, address, cardType, cardId);

        request.getSession().setAttribute("customer", customer);

        response.sendRedirect(request.getContextPath()+"/shopping/confirm.jsp");

    }

}
