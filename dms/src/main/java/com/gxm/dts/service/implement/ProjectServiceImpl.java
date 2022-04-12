package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.ProjectMapper;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类名 ProjectServiceImpl
 * @描述 一个继承了ProjectService接口的类，用于执行相关操作
 * @时间 2022-3
 */

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    // 查询项目列表
    @Override
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count, int userID) {
        PageHelper.startPage(page, count);
        List<Project> projectList = projectMapper.selectProjectWithUserID(userID);//需要修改
        return new PageInfo<>(projectList);
    }

    // 根据项目id查询详情，并使用Redis进行缓存管理
    public Project selectProjectDetailsWithID(Integer projectID) {
        Project project;
        Object object = redisTemplate.opsForValue().get("Project_" + projectID);

        // 检查redis 是否缓存对应id对象
        if(object != null) {
            project = (Project) object;
        } else {
            // 重新获取并保存
            project = projectMapper.selectProjectWithProjectID(projectID);
            if(project != null) {
                redisTemplate.opsForValue().set("Project_" + projectID, project);
            }
        }
        return project;
    }

    @Override
    public Project getProjectID(Integer projectID) {
        return projectMapper.getProjectId(projectID);
    }

    @Override
    public void addProject(Project project) {
        projectMapper.addProject(project);
    }

}
