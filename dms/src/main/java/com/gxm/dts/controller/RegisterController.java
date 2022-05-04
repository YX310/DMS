package com.gxm.dts.controller;

import com.gxm.dts.mapper.IndexMapper;
import com.gxm.dts.model.domain.User;
import com.gxm.dts.util.Constant;
import com.gxm.dts.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private IndexController indexController;
    @Autowired(required = false)
    private AdminController adminController;
    @Autowired
    private ProjectController projectController;

    @PostMapping(value = "/user/register")
    public String register(HttpServletRequest request,
                           User user,
                           Model model) {
        // if has user
        if (indexMapper.checkUser(user.getUsername()) != null) {
            model.addAttribute(Constant.ERROR_INFO, "用户已存在!");
            return "client/register";
        }

        // check email
        if (!Utils.checkEmail(user.getEmail())) {
            model.addAttribute(Constant.ERROR_INFO, "邮箱错误!");
            return "client/register";
        }

        // register
        indexMapper.register(user);
        // jump
        String role = user.getUser_role();
        model.addAttribute(Constant.ERROR_INFO, null);
        if (role.equals("管理员")) {
            return adminController.User(request);
        } else {
            HttpSession session = request.getSession(true);
            if (role.equals("项目经理") || role.equals("开发") || role.equals("测试")) {
                session.setAttribute("userId", user.getUser_id());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("email", user.getEmail());
                // Now the page we return is the login page, but also can jump to the index page.
                return projectController.Project(request);
            }
        }
        model.addAttribute(Constant.ERROR_INFO, "未知错误!");
        return "client/register";
    }
}
