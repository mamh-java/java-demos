package com.atguigu.javaweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AttrServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        //1.pageContext在ｓｅｒｖｌｅｔ中无法得到这个对象


        //2.ｒｅｑｕｅｓｔ
        Object atrr = req.getAttribute("r");
        out.println("requst: " + atrr);
        out.println("<br></br>");

        atrr = req.getSession().getAttribute("s");
        out.println("session: " + atrr);
        out.println("<br></br>");

        atrr = req.getServletContext().getAttribute("a");
        out.println("application: " + atrr);
        out.println("<br></br>");

        out.println("</body>");
        out.println("</html>");
    }
}
