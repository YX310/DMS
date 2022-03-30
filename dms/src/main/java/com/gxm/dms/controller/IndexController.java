package com.gxm.dms.controller;
import com.gxm.dms.mapper.ProjectMapper;
import com.gxm.dms.model.domain.User;
import com.gxm.dms.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String toMe(Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user",user);
        return "client/me";
    }

    //(前台)用户信息
    @GetMapping("/toUpdateMe")
    public String toUpdateMe(Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user",user);
        return "client/me_update";
    }
    //(前台)修改用户信息
    @RequestMapping("/updateMe")
    public String updateUserWithId(User user){
        userServiceImpl.updateMeWithId(user);
        return "client/me";
    }


}
