package com.gxm.dms.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.Project;

public interface ProjectService {

//    // 根据用户id查询项目
//    public Project selectProjectWithUserId(Integer user_id);

    // 分页查询项目列表，增加username
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count, int user_id);

    // 根据项目id查询项目的详情
    public Project selectProjectDetailsWithId(Integer project_id);
}
