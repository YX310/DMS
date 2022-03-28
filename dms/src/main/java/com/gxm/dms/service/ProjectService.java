package com.gxm.dms.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.Project;

public interface ProjectService {

    // 分页查询新闻资讯列表
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count);

    // 根据项目id查询项目的详情
    public Project selectProjectDetailsWithId(Integer project_id);
}
