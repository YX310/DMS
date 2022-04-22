package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface IProjectService {

//    // 根据用户id查询项目
//    public Project selectProjectWithUserId(Integer userID);

    // 分页查询项目列表，增加username
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count, int userID);

    // 根据项目id查询项目的详情
    public Project selectProjectDetailsWithId(Integer projectId);

    //获取home界面项目id
    Project getProjectId(Integer projectId);

    //新建项目
    public void addProject(Project project);

    //更新user_and_project表
    public void addUserAndProject(UserProject userProject);

    //更新（修改）项目信息
    public void updateProjectWithId(Project project);

    //根据项目id删除项目信息
    public void deleteProjectWithId(int projectId);

    //更新user_and_project表
    public void addProjectMember(ProjectMember projectMember);

    //查询项目成员
    public List<User> findProjectMemberByProjectId(Integer projectId);

    // 查询所有项目标题
    public Map<String, Integer> selectProject();
}
