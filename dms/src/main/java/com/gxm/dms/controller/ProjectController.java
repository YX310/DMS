package com.gxm.dms.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.Project;
import com.gxm.dms.service.implement.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String Project(HttpServletRequest request,@PathVariable("p") int page,
                       @RequestParam(value = "count", defaultValue = "5") int count){
        Integer user_id = (Integer) request.getSession(true).getAttribute("user_id");
        System.out.println("is int: " + user_id);
        PageInfo<Project> list = projectServiceImpl.selectProjectWithPage(page, count, user_id);
        request.setAttribute("data", list);
        request.setAttribute("page", page);
        request.setAttribute("count", list.getPages());
        return "client/home";
    }

    // 项目详情查询
    @GetMapping(value = "/project/{id}")
    public String getProjectById(@PathVariable("id") Integer id, HttpServletRequest request){
        Project project = projectServiceImpl.selectProjectDetailsWithId(id);
        if(project!=null){
            request.setAttribute("project",project);
            return "client/login";
        }else {
            return "client/home";
        }
    }

}
