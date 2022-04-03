package com.gxm.dms.mapper;

import com.gxm.dms.model.domain.Defect;
import com.gxm.dms.model.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@org.apache.ibatis.annotations.Mapper
public interface DefectMapper {

    // 缺陷查询
    @Select("SELECT * FROM defect")
    public List<Defect> selectDefectWithPage();

    // 根据id查询缺陷信息
    @Select("SELECT * FROM defect WHERE id=#{defect_id}")
    public Defect selectDefectWithId(String defect_id);

    //获取缺陷id
    @Select("SELECT * FROM defect WHERE defect_id = #{defect_id}")
    public Defect getDefectId(String defect_id);

    //新建缺陷
    @Insert("INSERT INTO defect(defect_name, defect_description, priority,designated_person, probability, defect_type,defect_state) VALUES(#{defect_name}, #{defect_description}, #{priority},#{designated_person}, #{probability}, #{defect_type},#{defect_state})")
    public void addDefect(Defect defect);

    //修改缺陷信息（前台）
    @Select("UPDATE defect SET defect_name = #{defect_name}, defect_description = #{defect_description},priority = #{priority},designated_person = #{designated_person},probability = #{probability},defect_type = #{defect_type},defect_state = #{defect_state} WHERE defect_id = #{defect_id}")
    public void updateDefectWithId(Defect defect);

    // 根据id缺陷信息
    @Select("DELETE FROM defect WHERE defect_id = #{defect_id}")
    public void deleteDefectWithId(String user_id);

}
