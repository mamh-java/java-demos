package com.atguigu.mvcapp.dao;

import com.atguigu.mvcapp.domain.FileUploadInfo;

import java.util.List;

public class FileUploadInfoDAOImpl extends DAO<FileUploadInfo> implements FileUploadInfoDAO {
    @Override
    public List<FileUploadInfo> getFiles() {
        String sql = "SELECT id, file_name fileName, file_path filePath, "
                + "file_desc fileDesc FROM upload_files";
        return getForList(sql);
    }

    @Override
    public void saveFiles(List<FileUploadInfo> list) {
        for (FileUploadInfo file : list) {
            saveFile(file);
        }
    }

    @Override
    public void saveFile(FileUploadInfo file) {
        String sql = "INSERT INTO upload_files (file_name, file_path, file_desc) VALUES (?, ?, ?)";
        update(sql, file.getFileName(), file.getFilePath(), file.getFileDesc());
    }
}
