package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;



@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    // home页面（分页展示）
    @GetMapping(value = "/home_project_list")
    String Project(HttpServletRequest request) {
        return this.Project(request, 1, 5);
    }

    @GetMapping("/page/{p}")
    public String Project(HttpServletRequest request,
                          @PathVariable("p") int page,
                          @RequestParam(value = "count", defaultValue = "5") int count) {
        Integer userID = (Integer) request.getSession(true).getAttribute("user_id");
        PageInfo<Project> list = projectServiceImpl.selectProjectWithPage(page, count, userID);
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
        Project project = projectServiceImpl.selectProjectDetailsWithID(id);

        // 检查项目是否存在加载对应界面
        if(project != null) {
            request.getSession(true).setAttribute("project", project);
            return "client/index";
        } else {
            return "client/home";
        }
    }

    //新建项目
    @RequestMapping("/toAddProject")
    public String toAddProject(){
        return "client/add_project";
    }

    @RequestMapping("/addProject")
    public String add(Project project){
        projectServiceImpl.addProject(project);
        return "redirect:/home_project_list";
    }

}
