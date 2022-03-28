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
    private IndexController indexController;
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    // home页面（分页）
    @GetMapping(value = "/project_list")
    private String news(HttpServletRequest request) {
        return this.news(request, 1, 5);
    }
    @GetMapping("/page/{p}")
    public String news(HttpServletRequest request,@PathVariable("p") int page,
                       @RequestParam(value = "count", defaultValue = "5") int count){
        PageInfo<Project> list = projectServiceImpl.selectProjectWithPage(page, count);
        request.setAttribute("data", list);
        request.setAttribute("page", page);
        request.setAttribute("count",projectServiceImpl.selectProjectWithPage(page, count).getPages());
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
