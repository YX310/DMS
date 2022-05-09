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
public interface ProjectMapper {

    //查找当前登录用户加入的项目
    @Select("SELECT * FROM project AS p INNER JOIN user_and_project AS up WHERE p.project_id=up.project_id and user_id=#{userId}")
    public List<Project> selectProjectWithUserId(int userId);

    // 查询所有项目标题
//    @Select("SELECT project_name, project_id FROM project")
//    public Map<String, Integer> selectProject();

    // 查询所有项目标题
    @Select("SELECT project_id, project_name FROM project WHERE project_name LIKE #{keyword} ORDER BY project_id DESC LIMIT 5")
    public List<SearchContent> selectProject(String keyword);

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

    //更新user_and_project表
    @Insert("INSERT INTO user_and_project(user_id, project_id) values(#{user_id},#{project_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addProjectMember(ProjectMember projectMember);

    //查询项目成员
    @Select("SELECT * FROM USER WHERE user_id IN (SELECT user_id FROM user_and_project WHERE project_id = #{project_id})")
    public List<User> findProjectMemberByProjectId(Integer projectId);

    // 查询项目成员id
    @Select("SELECT up.user_id FROM user_and_project AS up INNER JOIN user AS u WHERE u.user_id=up.user_id AND u.username=#{member} AND up.project_id=#{projectId}")
    public Integer selectMemberId(String member, int projectId);

    // 查询项目是否存在成员
    @Select("SELECT count(*) FROM user_and_project WHERE user_id=#{userId} AND project_id=#{projectId} LIMIT 1")
    public Integer checkUserInProject(int userId, int projectId);

    // 删除项目成员
    @Select("DELETE FROM user_and_project WHERE project_id=#{projectId}")
    public void deleteProjectMember(int projectId);

    // 查询项目创建者
    @Select("SELECT creator FROM project WHERE project_id=#{projectId}")
    public String selectCreator(int projectId);

    // 查询项目是否存在
    @Select("SELECT count(*) FROM project WHERE project_name=#{projectName}")
    public int selectProjectIdByProjectName(String projectName);
}
