package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.ProjectMapper;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.IProjectService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @类名 ProjectServiceImpl
 * @描述 一个继承了ProjectService接口的类，用于执行相关操作
 * @时间 2022-3
 */

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    // 查询项目列表
    @Override
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count, int userId) {
        PageHelper.startPage(page, count);
        List<Project> projectList = projectMapper.selectProjectWithUserId(userId);//需要修改
        return new PageInfo<>(projectList);
    }

    // 根据项目id查询详情，并使用Redis进行缓存管理
    @Override
    public Project selectProjectDetailsWithId(Integer projectId) {
        Project project;
        Object object = redisTemplate.opsForValue().get("Project_" + projectId);

        // 检查redis 是否缓存对应id对象
        if (object != null) {
            project = (Project) object;
        } else {
            // 重新获取并保存
            project = projectMapper.selectProjectWithProjectId(projectId);
            if (project != null) {
                redisTemplate.opsForValue().set("Project_" + projectId, project);
            }
        }
        return project;
    }

    @Override
    public Project getProjectId(Integer projectId) {
        return projectMapper.getProjectId(projectId);
    }

    @Override
    public void addProject(Project project) {
        projectMapper.addProject(project);
    }

    @Override
    public void addUserAndProject(UserProject userProject) {
        projectMapper.addUserAndProject(userProject);
    }

    @Override
    public void updateProjectWithId(Project project) {
        projectMapper.updateProjectWithId(project);
    }

    @Override
    public void deleteProjectWithId(int projectId) {
        projectMapper.deleteProjectWithId(projectId);
    }

    @Override
    public void addProjectMember(ProjectMember projectMember) {
        projectMapper.addProjectMember(projectMember);
    }

    @Override
    public List<User> findProjectMemberByProjectId(Integer projectId) {
        return projectMapper.findProjectMemberByProjectId(projectId);
    }

    @Override
    public List<SearchContent> selectProject(String keyword) {
        return projectMapper.selectProject('%' + keyword + '%');
    }

    @Override
    public Integer selectMemberId(String member, int projectId) {
        return projectMapper.selectMemberId(member, projectId);
    }

    @Override
    public boolean checkUserInProject(int userId, int projectId) {
        return projectMapper.checkUserInProject(userId, projectId) > 0;
    }

    @Override
    public void deleteProjectMember(int projectId) {
        projectMapper.deleteProjectMember(projectId);
    }

    @Override
    public String selectCreator(int projectId) {
        return projectMapper.selectCreator(projectId);
    }

    @Override
    public boolean checkProjectIsExist(String projectName) {
        return projectMapper.selectProjectIdByProjectName(projectName) > 0;
    }
}
