package com.gxm.dms.mapper;

import com.gxm.dms.model.domain.Defect;
import com.gxm.dms.model.domain.DefectProject;
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

    // 查询所有缺陷
    @Select("SELECT * FROM defect")
    public List<Defect> selectDefect();

    // 根据id查询缺陷信息
    @Select("SELECT * FROM defect WHERE id=#{defect_id}")
    public Defect selectDefectWithId(String defect_id);

    //获取缺陷id
    @Select("SELECT * FROM defect WHERE defect_id = #{defect_id}")
    public Defect getDefectId(String defect_id);

    //新建缺陷
    @Insert("INSERT INTO defect(defect_name, defect_description, priority,designated_person, probability, defect_type,defect_state,project_id,defect_creator) VALUES(#{defect_name}, #{defect_description}, #{priority},#{designated_person}, #{probability}, #{defect_type},#{defect_state},#{project_id},#{defect_creator})")
    public void addDefect(Defect defect);

    //修改缺陷信息（前台）
    @Select("UPDATE defect SET defect_name = #{defect_name}, defect_description = #{defect_description},priority = #{priority},designated_person = #{designated_person},probability = #{probability},defect_type = #{defect_type},defect_state = #{defect_state} WHERE defect_id = #{defect_id}")
    public void updateDefectWithId(Defect defect);

    // 根据id删除缺陷信息
    @Select("DELETE FROM defect WHERE defect_id = #{defect_id}")
    public void deleteDefectWithId(String defect_id);

    //根据当前用户id查找被指派的任务
    @Select("SELECT * FROM project AS p INNER JOIN defect AS d INNER JOIN USER AS u WHERE d.designated_person=u.username AND p.project_id=d.project_id AND user_id=#{user_id} ORDER BY defect_id DESC  LIMIT 10")
    public List<DefectProject> selectDesignatedPersonWithUserId(int user_id);

    //根据当前用户id查找当前用户报告的任务
    @Select("SELECT * FROM project AS p INNER JOIN defect AS d INNER JOIN user AS u WHERE d.defect_creator=u.username and p.project_id=d.project_id and user_id=#{user_id} ORDER BY defect_id DESC LIMIT 10")
    public List<DefectProject> selectDefectCreatorWithUserId(int user_id);

}
