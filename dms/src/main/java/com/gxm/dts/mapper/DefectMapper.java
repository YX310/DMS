package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
//    @Select("SELECT * FROM defect")
//    public List<Defect> selectDefect();

    // 模糊查询所有缺陷标题
    @Select("SELECT defect_id, defect_name FROM defect WHERE  defect_name LIKE #{keyword} ORDER BY defect_id DESC LIMIT 5")
    public List<SearchContent> selectDefect(String keyword);

    // 根据id查询缺陷信息
    @Select("SELECT * FROM defect WHERE id=#{defectId}")
    public Defect selectDefectWithId(String defectId);

    //获取缺陷id
    @Select("SELECT * FROM defect WHERE defect_id = #{defectId}")
    public Defect getDefectId(String defectId);

    //新建缺陷
    @Insert("INSERT INTO defect(defect_name, defect_description, priority,designated_person, probability, defect_type,start_date,finish_date,defect_state,project_id,defect_creator,creation_time,update_time) VALUES(#{defect_name}, #{defect_description}, #{priority},#{designated_person}, #{probability}, #{defect_type},#{start_date},#{finish_date},#{defect_state},#{project_id},#{defect_creator},#{creation_time},#{update_time})")
    @Options(useGeneratedKeys = true, keyProperty = "defect_id",keyColumn = "defect_id")
    public void addDefect(Defect defect);

    //修改缺陷信息（前台）
    @Select("UPDATE defect SET defect_name = #{defect_name}, defect_description = #{defect_description},priority = #{priority},designated_person = #{designated_person},progress = #{progress},probability = #{probability},defect_type = #{defect_type},defect_state = #{defect_state},start_date = #{start_date},finish_date = #{finish_date},update_time = #{update_time} WHERE defect_id = #{defect_id}")
    public void updateDefectWithId(Defect defect);

    // 根据id删除缺陷信息
    @Select("DELETE FROM defect WHERE defect_id = #{defectId}")
    public void deleteDefectWithId(String defectId);

    //根据当前用户id查找被指派的任务
    @Select("SELECT * FROM project AS p INNER JOIN defect AS d INNER JOIN USER AS u WHERE d.designated_person=u.username AND p.project_id=d.project_id AND user_id=#{userId} ORDER BY defect_id DESC  LIMIT 10")
    public List<DefectProject> selectDesignatedPersonWithUserId(int userId);

    //根据当前用户id查找当前用户报告的任务
    @Select("SELECT * FROM project AS p INNER JOIN defect AS d INNER JOIN user AS u WHERE d.defect_creator=u.username and p.project_id=d.project_id and user_id=#{userId} ORDER BY defect_id DESC LIMIT 10")
    public List<DefectProject> selectDefectCreatorWithUserId(int userId);

    //根据当前项目Id查找缺陷
    @Select("SELECT * FROM defect WHERE project_id=#{projectId}")
    public List<Defect> selectDefectWithProjectId(int projectId);

    //查找当前缺陷所属项目的信息
    @Select("SELECT * FROM project WHERE project_id IN (SELECT project_id FROM defect WHERE defect_id =#{defectId})")
    public DefectProject selectProjectMessageByDefectId(String defectId);
}
