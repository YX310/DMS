package com.gxm.dts.service;

import com.gxm.dts.model.domain.FileUpload;

import java.util.List;

public interface IFileUploadService {
    // 增加文件
    public void addFileUpload(FileUpload fileUpload);

    // 删除文件
    public void deleteFileUpload(int id);

    // 查找文件
    public List<FileUpload> selectFileUpload(int assocId, boolean isDefect);

    // 查找特定文件
    public FileUpload selectRepeatFileName(FileUpload fileUpload);
}
