package com.gxm.dts.controller;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.mapper.ProjectMapper;
import com.gxm.dts.model.domain.DefectProject;
import com.gxm.dts.model.domain.Project;
import com.gxm.dts.model.domain.User;
import com.gxm.dts.service.implement.DefectServiceImpl;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import com.gxm.dts.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @类名 IndexController
 * @描述 一个对控制页面操作的类
 * @时间 2022-3-22
 * @创建者 guanxiaomin
 */
@Controller
public class IndexController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private DefectServiceImpl defectServiceImpl;
    @Autowired
    private ProjectController projectController;

    @GetMapping(value = {"","/login"})
    public String toLogin(){
        return "client/login";
    }

    @GetMapping(value = {"/index"})
    public String toIndex(){
        return "client/index";
    }

    @GetMapping(value = {"/register"})
    public String toRegister(){
        return "client/register";
    }

    //(前台)用户信息
    @GetMapping("/toMe")
    public String toMe(HttpServletRequest request, Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user", user);
        return "client/me";
    }

    //(前台)用户信息
    @GetMapping("/toUpdateMe")
    public String toUpdateMe(Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user", user);
        return "client/meUpdate";
    }

    //(前台)修改用户信息
    @RequestMapping("/updateMe")
    public String updateUserWithId(User user){
        userServiceImpl.updateMeWithId(user);
        return "client/me";
    }

    //获取当前所选项目id
    @GetMapping(value = "/getProjectId")
    public String getProjectId(HttpServletRequest request,
                               @RequestParam("projectId") Integer id) {
        Project p  = projectServiceImpl.getProjectId(id);
        if (id == null) {
            return "client/home";
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("projectId", p.getProject_id());
            return projectController.getProjectById(request, id);
        }
    }

    @GetMapping(value = {"/toOverview"})
    public String toOverView(HttpServletRequest request, Integer id, Model model){
        //Project project = projectServiceImpl.selectProjectDetailsWithId(id);
        Project project = projectServiceImpl.getProjectId(id);
        model.addAttribute("project", project);
        return "client/overview";
    }

    @GetMapping(value = "/toWorkbench")
    public String toWorkbench(HttpServletRequest request,Integer id) {
        List<DefectProject> list=defectServiceImpl.selectDesignatedPersonWithUserID(id);
        List<DefectProject> list2=defectServiceImpl.selectDefectCreatorWithUserID(id);
        request.setAttribute("data4", list); //指派给我的任务
        request.setAttribute("data5", list2);//已报告的任务
        return "client/workbench";
    }
}
