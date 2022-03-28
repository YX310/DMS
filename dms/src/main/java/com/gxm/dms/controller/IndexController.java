package com.gxm.dms.controller;
import com.gxm.dms.mapper.IndexMapper;
import com.gxm.dms.mapper.ProjectMapper;
import com.gxm.dms.model.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
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
    private ProjectMapper projectMapper;

    @GetMapping(value = {"","/login"})
    public String toLogin(){
        return "client/login";
    }

//    @GetMapping(value = {"/home"})
//    public String toHome(){
//        return "client/home";
//    }

//    // 初始化首页展示页面
//    @GetMapping(value = {"/home"})
//    public String toHome(HttpServletRequest request){
//        List<Project> list = projectMapper.selectAllProject();
//        request.setAttribute("data", list);
//        return "client/home";
//    }

    @GetMapping(value = {"/index"})
    public String toIndex(){
        return "client/index";
    }

    @GetMapping(value = {"/register"})
    public String toRegister(){
        return "client/register";
    }

}
