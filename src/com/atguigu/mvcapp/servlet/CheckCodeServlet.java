package com.atguigu.mvcapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckCodeServlet", urlPatterns = {"/checkCodeServlet"})
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get request paramater CHECK_CODE_KEY_PARAM_NAME
        String paramCode = request.getParameter("CHECK_CODE_KEY_PARAM_NAME");

        //get session attribute value CHECK_CODE_KEY
        String sessionCode = (String) request.getSession().getAttribute("CHECK_CODE_KEY");

        System.out.println("paramCode = " + paramCode);
        System.out.println("session Code = " + sessionCode);

        if (paramCode != null && sessionCode != null && paramCode.equalsIgnoreCase(sessionCode)){
            System.out.println("equal, pass");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
