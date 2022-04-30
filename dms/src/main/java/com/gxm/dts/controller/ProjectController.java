package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.model.domain.ProjectMember;
import com.gxm.dts.model.domain.UserProject;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import com.gxm.dts.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.gxm.dts.util.Constant.SESSION_PROJECT_ID;

@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    // home页面（分页展示）
    @GetMapping(value = "/homeProjectList")
    String Project(HttpServletRequest request) {
        return this.Project(request, 1, 10);
    }

    @GetMapping("/page/{p}")
    public String Project(HttpServletRequest request,
                          @PathVariable("p") int page,
                          @RequestParam(value = "count", defaultValue = "10") int count) {
        Integer userId = (Integer) request.getSession(true).getAttribute("userId");
        PageInfo<Project> list = projectServiceImpl.selectProjectWithPage(page, count, userId);
        request.setAttribute("data", list);
        request.setAttribute("page", page);
        request.setAttribute("count", list.getPages());
        return "client/home";
    }

    // 项目详情查询
    @GetMapping(value = "/project/{id}")
    public String getProjectById(HttpServletRequest request,
                                 @PathVariable("id") Integer id) {
        // 根据项目id查询具体项目
        Project project = projectServiceImpl.selectProjectDetailsWithId(id);
        // 检查项目是否存在加载对应界面
        if(project != null) {
            System.out.println(project);
            request.getSession(true).setAttribute(SESSION_PROJECT_ID, id);
            return "redirect:/toOverview?id=" + id;
        } else {
            return "client/home";
        }
    }

    //新建项目
    @RequestMapping("/toAddProject")
    public String toAddProject(){
        return "client/addProject";
    }

    @RequestMapping("/addProject")
    public String add(Project project, UserProject userProject, Model model) {
        String[] projectMembers = project.getProject_member().split(";");
        List<Integer> isSystemUser = new ArrayList<>();
        List<String> isNotSystemUser = new ArrayList<>();
        isSystemUser.add(userProject.getUser_id());
        for (String projectMember : projectMembers) {
            Integer userId = userServiceImpl.findUserIdByUsername(projectMember);
            if (userId == null) {
                isNotSystemUser.add(projectMember);
                System.out.println(projectMember);
                continue;
            }
            if (userId != userProject.getUser_id() && userId > 0) {
                isSystemUser.add(userId);
            }
        }
        if (isNotSystemUser.size() != 0) {
            model.addAttribute("isNotSystemUser", isNotSystemUser);

            return "client/addProject";
        }

        projectServiceImpl.addProject(project);
        userProject.setProject_id(project.getProject_id());
        System.out.println("userProject" + userProject);

        for (Integer userId : isSystemUser) {
            //向user_and_project表插入数据
            projectServiceImpl.addProjectMember(new ProjectMember(userId, project.getProject_id()));
        }
        return "redirect:/homeProjectList";
    }

    //更新（修改）项目信息
    @RequestMapping("/toUpdateProject")
    public String toUpdateProject(int id, Model model) {
        Project project = projectServiceImpl.getProjectId(id);
        model.addAttribute("project", project);
        return "client/projectUpdate";
    }

    //修改项目信息
    @RequestMapping("/updateProject")
    public String updateDefectWithId(Project project) {
        Integer id = project.getProject_id();
        projectServiceImpl.updateProjectWithId(project);
        System.out.println("执行了" + project);
        return "redirect:/toOverview?id=" + id; //redirect重定向
    }

    //删除项目信息
    @RequestMapping("/deleteProject")
    public String deleteProject(int id) {
        projectServiceImpl.deleteProjectWithId(id);
        return "redirect:/homeProjectList"; //redirect重定向
    }


}
