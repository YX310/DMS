package com.gxm.dts.service.implement;

import com.gxm.dts.mapper.FileUploadMapper;
import com.gxm.dts.mapper.UpdateRecordMapper;
import com.gxm.dts.model.domain.UpdateRecord;
import com.gxm.dts.service.IUpdateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UpdateRecordServiceImpl implements IUpdateRecordService {
    @Autowired
    private UpdateRecordMapper updateRecordMapper;

    @Override
    public List<UpdateRecord> selectUpdateRecordWithAssocId(int assocId) {
        return updateRecordMapper.selectUpdateRecordWithAssocId(assocId);
    }

    @Override
    public List<UpdateRecord> selectUpdateRecordWithProjectId(int projectId) {
        return updateRecordMapper.selectUpdateRecordWithProjectId(projectId);
    }

    @Override
    public void addUpdateRecord(UpdateRecord updateRecord) {
        updateRecordMapper.addUpdateRecord(updateRecord);
    }

    @Override
    public void deleteUpdateRecordWithAssocId(int id) {
        updateRecordMapper.deleteUpdateRecordWithAssocId(id);
    }
}
