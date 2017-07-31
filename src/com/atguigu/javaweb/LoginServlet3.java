package com.atguigu.javaweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "select count(tester) from st_tester where tester = ? and password = ?";
        ResultSet resultSet = null;

        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///smoketest";
            String user = "smoketest";
            String pass = "smoketest";
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count>0){
                    out.println("hello: " + username);
                }   else{
                    out.println("sorry: " + username);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet!=null){
                
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("doPost()....");
    }
}
