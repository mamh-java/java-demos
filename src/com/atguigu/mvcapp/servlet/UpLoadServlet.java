package com.atguigu.mvcapp.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UpLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File("/tmp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 5);
        System.out.println("=============================================================");

        try {
            // 得到FileItem对象集合
            List<FileItem> items = upload.parseRequest(req);
            //遍历, 判断是否是表单域，还是文件域
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println("=fileitem=" + name + "   " + value);
                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

                    System.out.println("-fieldName    = " + fieldName);
                    System.out.println("-fileName     = " + fileName);
                    System.out.println("-contentType  = " + contentType);
                    System.out.println("-isInMemory   = " + isInMemory);
                    System.out.println("-sizeInBytes  = " + sizeInBytes);

                    InputStream in = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    OutputStream out = new FileOutputStream("/tmp/" + fileName);
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();

                }//end if
            }//end for

            //若是文件域就把文件保存到某个目录下面
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }
}
