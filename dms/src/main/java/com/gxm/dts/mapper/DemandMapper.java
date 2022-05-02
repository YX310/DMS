package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@org.apache.ibatis.annotations.Mapper
public interface DemandMapper {

    // 根据id查询需求信息
    @Select("SELECT * FROM demand WHERE id=#{demandId}")
    public Demand selectDemandWithId(int demandId);

    // 查询所有需求标题
    @Select("SELECT demand_id, demand_name FROM demand WHERE demand_name LIKE #{keyword} ORDER BY demand_id DESC LIMIT 5")
    public List<SearchContent> selectDemand(String keyword);

    //获取需求id
    @Select("SELECT * FROM demand WHERE demand_id = #{demandId}")
    public Demand getDemandId(int demandId);

    //根据当前项目Id查找需求
    @Select("SELECT * FROM demand WHERE project_id=#{projectId}")
    public List<Demand> selectDemandWithProjectId(int projectId);

    //新建需求
    @Insert("INSERT INTO demand(project_id, demand_name, demand_description,designated_person, demand_state, priority,start_date,finish_date,creation_time,demand_record,update_time,demand_creator) VALUES(#{project_id}, #{demand_name}, #{demand_description},#{designated_person}, #{demand_state}, #{priority},#{start_date},#{finish_date},#{creation_time},#{demand_record},#{update_time},#{demand_creator})")
    @Options(useGeneratedKeys = true, keyProperty = "demand_id",keyColumn = "demand_id")
    public void addDemand(Demand demand);

    //修改需求信息
    @Select("UPDATE demand SET demand_name = #{demand_name}, demand_description = #{demand_description},designated_person = #{designated_person},demand_state = #{demand_state},priority = #{priority},progress = #{progress},start_date = #{start_date},finish_date = #{finish_date},update_time = #{update_time} WHERE demand_id = #{demand_id}")
    public void updateDemandWithId(Demand demand);

    //根据当前用户id查找被指派的需求
    @Select("SELECT * FROM project AS p INNER JOIN demand AS d INNER JOIN USER AS u WHERE d.designated_person=u.username AND p.project_id=d.project_id AND user_id=#{userId} ORDER BY demand_id DESC LIMIT 10")
    public List<DemandProject> selectDemandDesignatedPersonWithUserId(int userId);

    //根据当前用户id查找当前用户报告的需求
    @Select("SELECT * FROM project AS p INNER JOIN demand AS d INNER JOIN user AS u WHERE d.demand_creator=u.username and p.project_id=d.project_id and user_id=#{userId} ORDER BY demand_id DESC LIMIT 10")
    public List<DemandProject> selectDemandCreatorWithUserId(int userId);

    // 根据id删除需求信息
    @Select("DELETE FROM demand WHERE demand_id = #{demandId}")
    public void deleteDemandWithId(int demandId);

    //查找当前需求所属项目的信息
    @Select("SELECT * FROM project WHERE project_id IN (SELECT project_id FROM demand WHERE demand_id =#{demandId})")
    public DemandProject selectProjectMessageByDemandId(Integer demandId);
}
