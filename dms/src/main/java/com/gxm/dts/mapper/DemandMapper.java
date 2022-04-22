package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.Demand;
import com.gxm.dts.model.domain.DemandFile;
import com.gxm.dts.model.domain.UpdateDemand;
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
    @Select("SELECT demand_name, defect_id FROM demand")
    public Map<String, Integer> selectDemand();

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

    //新增文件
    @Insert("INSERT INTO demand_and_file_path(demand_id, file_path) values(#{demand_id},#{file_path})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    public void addDemandFile(DemandFile demandFile);

    //修改需求信息
    @Select("UPDATE demand SET demand_name = #{demand_name}, demand_description = #{demand_description},designated_person = #{designated_person},demand_state = #{demand_state},priority = #{priority},progress = #{progress},start_date = #{start_date},finish_date = #{finish_date},update_time = #{update_time} WHERE demand_id = #{demand_id}")
    public void updateDemandWithId(Demand demand);

    // 根据id删除需求信息
    @Select("DELETE FROM demand WHERE demand_id = #{demandId}")
    public void deleteDemandWithId(int demandId);

    //查询demand_update_record表
    @Select("SELECT * FROM demand_update_record WHERE demand_id=#{demandId}")
    public List<UpdateDemand> selectUpdateDemandWithDemandId(int demandId);

    //向demand_update_record表插入数据
    @Insert("INSERT INTO demand_update_record(demand_id, update_time, record_content,update_person) values(#{demand_id},#{update_time},#{record_content},#{update_person})")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    public void addUpdateDemand(UpdateDemand updateDemand);
}
