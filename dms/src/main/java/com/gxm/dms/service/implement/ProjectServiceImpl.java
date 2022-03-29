package com.gxm.dms.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dms.mapper.IndexMapper;
import com.gxm.dms.mapper.ProjectMapper;
import com.gxm.dms.model.domain.Project;
import com.gxm.dms.model.domain.User;
import com.gxm.dms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @类名 ProjectServiceImpl
 * @描述 一个继承了ProjectService接口的类，用于执行相关操作
 * @时间 2020-3-38
 */

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RedisTemplate redisTemplate;

//    public String getUserId(HttpSession session, HttpServletRequest request, User user) {
//        // if has user
//        if (mapper.checkUser(user.getUsername()) != null)
//            session.setAttribute("user_id", user.getUser_id());
//        return null;
//    }
    // 查询项目列表
    @Override
    public PageInfo<Project> selectProjectWithPage(Integer page, Integer count) {
        PageHelper.startPage(page, count);
//        List<Project> projectList = projectMapper.selectAllProject();//需要修改
        List<Project> projectList = projectMapper.selectProjectWithUserId();//需要修改
        PageInfo<Project> pageInfo=new PageInfo<>(projectList);
        return pageInfo;
    }

    // 根据项目id查询详情，并使用Redis进行缓存管理
    public Project selectProjectDetailsWithId(Integer project_id){
        Project project = null;
        Object o = redisTemplate.opsForValue().get("Project_" + project_id);
        if(o!=null){
            project=(Project)o;
        }else{
            project = projectMapper.selectProjectWithProject_id(project_id);
            if(project!=null){
                redisTemplate.opsForValue().set("Project_" + project_id,project);
            }
        }
        return project;
    }

}
