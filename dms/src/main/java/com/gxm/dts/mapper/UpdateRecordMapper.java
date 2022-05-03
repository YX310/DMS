package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.UpdateRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.apache.ibatis.annotations.Mapper
public interface UpdateRecordMapper {

    //根据缺陷/需求id查询update_record表
    @Select("SELECT * FROM update_record WHERE assoc_id=#{assocId}")
    public List<UpdateRecord> selectUpdateRecordWithAssocId(int assocId);

    //根据项目id查询update_record表
    @Select("SELECT * FROM update_record WHERE project_id=#{projectId} ORDER BY update_time DESC")
    public List<UpdateRecord> selectUpdateRecordWithProjectId(int projectId);

    //根据当前用户名查询update_record表
    @Select("SELECT * FROM update_record WHERE update_person=#{updatePerson} ORDER BY update_time DESC")
    public List<UpdateRecord> selectUpdateRecordWithUpdatePerson(String updatePerson);

    //向update_record表插入数据
    @Insert("INSERT INTO update_record(assoc_id, update_time, record_content, update_person, is_defect, project_id, assoc_title)" +
            " values(#{assoc_id},#{update_time},#{record_content},#{update_person},#{is_defect},#{project_id},#{assoc_title})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    public void addUpdateRecord(UpdateRecord updateRecord);

    //根据id删除update_record记录
    @Select("DELETE FROM update_record WHERE id=#{id}")
    public void deleteUpdateRecordWithAssocId(int id);
}
