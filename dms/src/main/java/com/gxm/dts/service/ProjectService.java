package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Project;


public interface ProjectService {

//    // 根据用户id查询项目
//    public Project selectProjectWithUserId(Integer user_id);

    // 分页查询项目列表，增加username
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count, int user_id);

    // 根据项目id查询项目的详情
    public Project selectProjectDetailsWithId(Integer project_id);

    //获取home界面项目id
    Project getProjectId(Integer project_id);

    //新建项目
    public void addProject(Project project);
}
