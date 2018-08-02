package com.atguigu.mvcapp.servlet;

import com.atguigu.mvcapp.dao.CriteriaCumtomer;
import com.atguigu.mvcapp.dao.CustomerDAO;
import com.atguigu.mvcapp.dao.CustomerDAOFactory;
import com.atguigu.mvcapp.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = CustomerDAOFactory.getInstance().getCustomerDAO();

    //private CustomerDAO customerDAO = new CustomerDAOXmlImpl(); // 面向接口变成，如果下面底层的存储换为xml的话只需要重新实现一个xml的实现


    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        if (customerDAO.getCountWithName(name) > 0) {
            request.setAttribute("message", "name exists: " + name);
            request.getRequestDispatcher("newcustomer.jsp").forward(request, response);
        } else {
            Customer customer = new Customer(name, address, phone);
            customerDAO.save(customer);
            response.sendRedirect("success.jsp");
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
            Customer customer = customerDAO.get(id);
            request.setAttribute("customer", customer);
        } catch (Exception e) {

        }

        request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
            customerDAO.delete(id);
        } catch (Exception e) {

        }
        response.sendRedirect("query.do");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        String name = request.getParameter("name");
        String oldname = request.getParameter("oldName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        System.out.println(id);
        if (!oldname.equals(name)) {
            if (customerDAO.getCountWithName(name) > 0) {
                request.setAttribute("message", "用户名已经被占用：" + name);
                request.getRequestDispatcher("updatecustomer.jsp").forward(request, response);
                return;
            }
        }

        Customer customer = new Customer(id, name, address, phone);
        customerDAO.update(customer);

        response.sendRedirect("query.do");
    }

    protected void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        //1. 调用 customerDAO 中的getAll() 方法得到所有的集合
        //        List<Customer> all = customerDAO.getAll();
        List<Customer> all = customerDAO.getForListWithCriteriaCustomer(new CriteriaCumtomer(name, address, phone));


        //2.把得到的所有的customer 集合放到request属性中
        request.setAttribute("customers", all);
        System.out.println(all);

        //3.转发
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        //去除 路径中开始的/ 和结尾的 .do

        String methodName = servletPath.substring(1);
        methodName = methodName.substring(0, methodName.length() - 3);


        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
