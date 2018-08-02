package com.atguigu.mvcapp.domain;

import java.io.Serializable;

public class FileUploadInfo implements Serializable {
    //文件的信息相关的一个javabean类
    private int id;

    private String fileName;
    private String filePath;
    private String fileDesc;

    public FileUploadInfo() {
    }

    public FileUploadInfo(String fileName, String filePath, String fileDesc) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileDesc = fileDesc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileUploadInfo that = (FileUploadInfo) o;

        if (id != that.id) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;
        return fileDesc != null ? fileDesc.equals(that.fileDesc) : that.fileDesc == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (fileDesc != null ? fileDesc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileUploadInfo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileDesc='" + fileDesc + '\'' +
                '}';
    }
}
