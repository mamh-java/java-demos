package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.FileUploadInfo;

import java.util.List;

public interface FileUploadInfoDAO {


    List<FileUploadInfo> getFiles();


    void saveFiles(List<FileUploadInfo> list);

    void saveFile(FileUploadInfo fileUploadInfo);

}
