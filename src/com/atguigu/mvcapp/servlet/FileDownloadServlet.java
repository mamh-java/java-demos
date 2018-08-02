package com.atguigu.mvcapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "downLoadServlet", urlPatterns = {"/downLoadServlet"})
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition", "attachment;filename=abc.txt");

        String pptFileNmae = "/tmp/xxx.ppt";

        InputStream in = new FileInputStream(pptFileNmae);

        OutputStream out = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();


    }
}
