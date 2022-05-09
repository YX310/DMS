package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.model.domain.ProjectMember;
import com.gxm.dts.model.domain.UserProject;
import com.gxm.dts.service.implement.DefectServiceImpl;
import com.gxm.dts.service.implement.DemandServiceImpl;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import com.gxm.dts.service.implement.UserServiceImpl;
import com.gxm.dts.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.gxm.dts.util.Constant.*;
import static com.gxm.dts.util.Utils.applyInfo;

@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private DemandServiceImpl demandServiceImpl;
    @Autowired
    private DefectServiceImpl defectServiceImpl;

    // home页面（分页展示）
    @GetMapping(value = "/homeProjectList")
    String Project(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);
        applyInfo(session, model, CREATE_INFO);
        applyInfo(session, model, DELETE_INFO);
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

    private void setNotSystemUser(List<String> isNotSystemUser, HttpSession session, String info) {
        if (isNotSystemUser.size() != 0) {
            StringBuilder tmp = new StringBuilder("不存在用户：");
            for (String item : isNotSystemUser) tmp.append(item).append(",");
            session.setAttribute(info, tmp.deleteCharAt(tmp.length() - 1));
        }
    }

    //新建项目
    @RequestMapping("/toAddProject")
    public String toAddProject(HttpSession session, Model model) {
        applyInfo(session, model, ERROR_INFO);
        return "client/addProject";
    }

    @RequestMapping("/addProject")
    public String add(HttpSession session,
                      Project project,
                      UserProject userProject) {
        if (projectServiceImpl.checkProjectIsExist(userProject.getProject_name())) {
            session.setAttribute(ERROR_INFO, "项目已存在");
            return "redirect:/toAddProject";
        }
        String creator = userServiceImpl.findUsernameById(userProject.getUser_id());
        if (!project.getProject_member().contains(creator)) {
            project.setProject_member(creator + ";" + project.getProject_member());
        }
        String[] projectMembers = project.getProject_member().split(";");
        List<Integer> isSystemUser = new ArrayList<>();
        List<String> isNotSystemUser = new ArrayList<>();
        isSystemUser.add(userProject.getUser_id());
        List<String> existMember = filterUser(isSystemUser, isNotSystemUser, projectMembers, userProject.getUser_id());

        StringBuilder recordMember = new StringBuilder();
        for (String item : existMember) recordMember.append(item).append(";");
        project.setProject_member(recordMember.deleteCharAt(recordMember.length() - 1).toString());

        projectServiceImpl.addProject(project);
        userProject.setProject_id(project.getProject_id());
        if (Constant.DEBUG) System.out.println("userProject" + userProject);

        for (Integer userId : isSystemUser) {
            //向user_and_project表插入数据
            projectServiceImpl.addProjectMember(new ProjectMember(userId, project.getProject_id()));
        }
        session.setAttribute(CREATE_INFO, "");
        setNotSystemUser(isNotSystemUser, session, CREATE_INFO);
        return "redirect:/homeProjectList";
    }

    private List<String> filterUser(List<Integer> isSystemUser, List<String> isNotSystemUser, String[] projectMembers, int creatorId) {
        List<String> existMember = new ArrayList<>();
        for (String projectMember : projectMembers) {
            Integer userId = userServiceImpl.findUserIdByUsername(projectMember);
            if (userId == null) {
                isNotSystemUser.add(projectMember);
                System.out.println(projectMember);
                continue;
            } else {
                existMember.add(projectMember);
            }
            if (userId != creatorId && userId > 0) {
                isSystemUser.add(userId);
            }
        }
        return existMember;
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
    public String updateDefectWithId(HttpSession session,
                                     Project project) {
        int projectId = project.getProject_id();
        int creatorId = userServiceImpl.findUserIdByUsername(projectServiceImpl.selectCreator(projectId));
        String creator = userServiceImpl.findUsernameById(creatorId);
        if (!project.getProject_member().contains(creator)) {
            project.setProject_member(creator + ";" + project.getProject_member());
        }
        String[] projectMembers = project.getProject_member().split(";");
        List<Integer> isSystemUser = new ArrayList<>();
        List<String> isNotSystemUser = new ArrayList<>();
        System.out.println("project.getCreator:" + project.getCreator());
        isSystemUser.add(creatorId);
        List<String> existMember = filterUser(isSystemUser, isNotSystemUser, projectMembers, creatorId);

        StringBuilder recordMember = new StringBuilder();
        for (String item : existMember) recordMember.append(item).append(";");
        project.setProject_member(recordMember.deleteCharAt(recordMember.length() - 1).toString());

        projectServiceImpl.updateProjectWithId(project);
        projectServiceImpl.deleteProjectMember(projectId);
        for (Integer userId : isSystemUser) {
            //向user_and_project表插入数据
            projectServiceImpl.addProjectMember(new ProjectMember(userId, project.getProject_id()));
        }
        session.setAttribute(UPDATE_INFO, "");
        setNotSystemUser(isNotSystemUser, session, UPDATE_INFO);
        System.out.println("执行了" + project);
        return "redirect:/toOverview?id=" + projectId; //redirect重定向
    }

    //删除项目信息
    @RequestMapping("/deleteProject")
    public String deleteProject(HttpSession session,
                                int id) {
        projectServiceImpl.deleteProjectWithId(id);
        defectServiceImpl.deleteDefectWithProjectId(id);
        demandServiceImpl.deleteDemandWithProjectId(id);
        session.setAttribute(DELETE_INFO, "");
        return "redirect:/homeProjectList"; //redirect重定向
    }
}
