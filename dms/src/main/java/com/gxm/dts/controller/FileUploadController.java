package com.gxm.dts.controller;

import com.gxm.dts.model.domain.FileUpload;
import com.gxm.dts.service.implement.FileUploadServiceImpl;
import com.gxm.dts.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {
    @Autowired
    private FileUploadServiceImpl fileUploadServiceImpl;

    @Value("${web.upload-path}")
    private String uploadPath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    public void saveFile(MultipartFile[] files, int assocId, String prefix, boolean isDefect) {
        // 检查是否上传文件
        if (files.length > 0 && !("").equals(files[0].getOriginalFilename())) {
            // 初始化日期和存储路径
            String format = sdf.format(new Date());
            File folder = new File(uploadPath + format);
            if (!folder.isDirectory()) {
                boolean res = folder.mkdirs();
                if (Constant.DEBUG) System.out.println("folder res: " + res);
            }

            // 初始化文件路径
            int i = 0;
            // 遍历存储
            for (MultipartFile file : files) {
                if (Constant.DEBUG) System.out.println("defect: " + file.getOriginalFilename());
                String oldName = file.getOriginalFilename();
                if (oldName == null) oldName = "";
                String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                try {
                    file.transferTo(new File(folder, newName));
                    String fileRes = prefix + format + newName;
                    if (Constant.DEBUG) System.out.println("fileRes: " + fileRes);
                    FileUpload fileUpload = new FileUpload();
                    fileUpload.setIs_defect(isDefect);
                    fileUpload.setFile_name(oldName);
                    fileUpload.setAssoc_id(assocId);
                    updateRepeatFileName(fileUpload);
                    fileUpload.setFile_path(fileRes);
                    fileUploadServiceImpl.addFileUpload(fileUpload);
                    if (Constant.DEBUG) System.out.println("fileRes: " + ++i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateRepeatFileName(FileUpload fileUpload) {
        FileUpload fu = fileUploadServiceImpl.selectRepeatFileName(fileUpload);
        String fileName = fileUpload.getFile_name();
        int fileExtensionIndex = fileName.lastIndexOf(".");
        String fileExtension = fileName.substring(fileExtensionIndex);
        String firstFileName = fileName.substring(0, fileExtensionIndex);
        int fileIndex = 1;
        while (fu != null) {
            // test (1).txt
            fileUpload.setFile_name(firstFileName + "(" + fileIndex + ")" + fileExtension);
            fu = fileUploadServiceImpl.selectRepeatFileName(fileUpload);
            fileIndex++;
        }
    }

    // 删除文件
    public void deleteFile(int fileId) {
        fileUploadServiceImpl.deleteFileUpload(fileId);
    }

    // 查询文件
    public List<FileUpload> selectFileUpload(int assocId, boolean isDefect) {
        return fileUploadServiceImpl.selectFileUpload(assocId, isDefect);
    }
}
