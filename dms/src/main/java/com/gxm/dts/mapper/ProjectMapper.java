package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.model.domain.UserProject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.apache.ibatis.annotations.Mapper
public interface ProjectMapper {

    //查找当前登录用户加入的项目
    @Select("SELECT * FROM project AS p INNER JOIN user_and_project AS up WHERE p.project_id=up.project_id and user_id=#{user_id}")
    public List<Project> selectProjectWithUserID(int user_id);

    // 查询所有的项目信息
    @Select("SELECT * FROM project WHERE project_id = #{projectId}")
    public List<Project> selectProject(Integer projectId);

    // 根据项目id查询项目信息
    @Select("SELECT * FROM project WHERE project_id = #{projectId}")
    public Project selectProjectWithProjectId(Integer projectId);

    //获取项目id
    @Select("SELECT * FROM project WHERE project_id = #{projectId}")
    public Project getProjectId(Integer projectId);

    //新建项目
    @Insert({"INSERT INTO project values(#{project_id},#{project_name}, #{creator}, #{creation_date},#{project_description}, #{project_member})"})
    @Options(useGeneratedKeys = true, keyProperty = "project_id", keyColumn = "project_id")
    public void addProject(Project project);

    //更新user_and_project表
    @Insert("INSERT INTO user_and_project(user_id, project_id) values(#{user_id},#{project_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addUserAndProject(UserProject userProject);

    //修改项目信息
    @Select("UPDATE project SET project_name = #{project_name},project_description = #{project_description},project_member = #{project_member} WHERE project_id = #{project_id}")
    public void updateProjectWithId(Project project);

    // 根据id删除项目信息
    @Select("DELETE FROM project WHERE project_id = #{project_id}")
    public void deleteProjectWithId(int project_id);
}
