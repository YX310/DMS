package com.gxm.dts.service;

import com.gxm.dts.model.domain.UpdateRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUpdateRecordService {
    //查询update_record表
    public List<UpdateRecord> selectUpdateRecordWithAssocId(int assocId);

    //向update_record表插入数据
    public void addUpdateRecord(UpdateRecord updateRecord);

    //根据id删除update_record记录
    public void deleteUpdateRecordWithAssocId(int id);

    //根据project_id记录
    public List<UpdateRecord> selectUpdateRecordWithProjectId(int projectId);
}
