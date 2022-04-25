package com.gxm.dts.service.implement;

import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.mapper.FileUploadMapper;
import com.gxm.dts.model.domain.FileUpload;
import com.gxm.dts.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileUploadServiceImpl  implements IFileUploadService {
    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Override
    public void addFileUpload(FileUpload fileUpload) {
        fileUploadMapper.addFileUpload(fileUpload);
    }

    @Override
    public void deleteFileUpload(int id) {
        fileUploadMapper.deleteFileUpload(id);
    }

    @Override
    public List<FileUpload> selectFileUpload(int assocId, boolean isDefect) {
        return fileUploadMapper.selectFileUpload(assocId, isDefect);
    }

    @Override
    public FileUpload selectRepeatFileName(FileUpload fileUpload) {
        return fileUploadMapper.selectRepeatFileName(fileUpload);
    }
}
