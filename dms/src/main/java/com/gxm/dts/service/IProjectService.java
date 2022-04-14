package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.model.domain.UserProject;


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
}
