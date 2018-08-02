package com.atguigu.mvcapp.servlet;

import com.atguigu.mvcapp.dao.FileUploadInfoDAO;
import com.atguigu.mvcapp.dao.FileUploadInfoDAOImpl;
import com.atguigu.mvcapp.domain.FileUploadInfo;
import com.atguigu.mvcapp.exception.InvalidExtNameException;
import com.atguigu.mvcapp.utils.AlgorithmUtils;
import com.atguigu.mvcapp.utils.FileUploadAppProperties;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@WebServlet(name = "upLoadServlet", urlPatterns = {"/upLoadServlet"})
public class FileUploadServlet extends HttpServlet {
    private static final String ROOT_PATH = "/tmp";

    private FileUploadInfoDAO dao = new FileUploadInfoDAOImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String path = "";
        try {
            // 得到FileItem对象集合
            ServletFileUpload upload = getServletFileUpload();
            List<FileItem> items = upload.parseRequest(req);

            //填充FileUploadInfo的集合,同时填充这个集合uploadFiles
            Map<String, FileItem> fileUploadMap = new HashMap<>();
            List<FileUploadInfo> fileUploadInfoList = new ArrayList<>();
            //填充bean集合
            fillBeans(items, fileUploadInfoList, fileUploadMap);

            //校验文件扩展名
            vaidateExtName(fileUploadInfoList);

            //校验文件大小,这个不需要直接写，这个在解析时候已经校验了，如果大小不符合会抛出异常的

            //以上都通过就进行文件的上传操作
            uploadFile(fileUploadMap);

            //把上传成功的文件的信息保存到数据库中
            //saveBeans(fileUploadInfoList);
            req.setAttribute("message", "上传文件成功！");
            path = "/updown/upload.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            path = "/updown/upload.jsp";
            req.setAttribute("message", e.getMessage());
        }

        req.getRequestDispatcher(path).forward(req, resp);

    }//end doPost

    private void saveBeans(List<FileUploadInfo> list) {
        dao.saveFiles(list);
    }

    /**
     * 文件上传方法，遍历ｍａｐ中的文件，上传
     *
     * @param map
     */
    private void uploadFile(Map<String, FileItem> map) {
        for (Map.Entry<String, FileItem> entry : map.entrySet()) {
            String filePath = entry.getKey();
            FileItem fileItem = entry.getValue();
            try {
                InputStream inputStream = fileItem.getInputStream();
                OutputStream outputStream = new FileOutputStream(filePath);
                byte[] buffer = new byte[10240];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//end for()
    }

    private void vaidateExtName(List<FileUploadInfo> list) {
        String exts = FileUploadAppProperties.getInstance().getProperty("exts");
        List<String> extList = Arrays.asList(exts.split(","));
        for (FileUploadInfo fileUploadInfo : list) {
            String fileName = fileUploadInfo.getFileName();
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (!extList.contains(extName)) {
                //只要有一个不符合就都不允许上传，这里抛出一个异常
                throw new InvalidExtNameException("文件的扩展名不合法：" + fileName);
            }
        }
    }

    private void fillBeans(List<FileItem> items, List<FileUploadInfo> list, Map<String, FileItem> map) {
        //1. 遍历ｉｔｅｍｓ集合，先得到ｄｅｓｃ的ｍａｐ，其中键是ｆｉｌｅｄＮａｍｅ表单域中的ｄｅｓｃ１，ｄｅｓｃ２等
        Map<String, String> descMap = new HashMap<>();
        for (FileItem item : items) {
            if (item.isFormField())
                try {
                    descMap.put(item.getFieldName(), item.getString("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
        }
        for (FileItem item : items) {
            if (!item.isFormField()) {
                //文件的这个表单域，file1,file2,file3,.....
                String fieldName = item.getFieldName(); //下面取到最后一个，最后一个是一个索引，一个数字，这个和ｄｅｓｃ是一一对应的
                String index = fieldName.replace("file", "");
                String fileName = item.getName();
                String desc = descMap.get("desc" + index);
                String filePath = getFilePath(fileName);
                FileUploadInfo info = new FileUploadInfo(fileName, filePath, desc);
                System.out.println("fillBeans = " + info);
                list.add(info);
                map.put(filePath, item);
            }
        }

    }

    private String getFilePath(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));//获取文件的扩展名
        Random random = new Random();
        int randomNum = random.nextInt(1000000);
        try {
            fileName = AlgorithmUtils.sha1("" + System.currentTimeMillis() + randomNum);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String filePath = getServletContext().getRealPath(ROOT_PATH) + "/" + fileName + extName;
        return filePath;
    }

    private ServletFileUpload getServletFileUpload() {

        String fileMaxSize = FileUploadAppProperties.getInstance().getProperty("file.max.size");
        String totalMaxSize = FileUploadAppProperties.getInstance().getProperty("total.max.size");


        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 500);
        factory.setRepository(new File("/tmp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(Integer.parseInt(fileMaxSize));
        upload.setSizeMax(Integer.parseInt(totalMaxSize));
        return upload;
    }
}
