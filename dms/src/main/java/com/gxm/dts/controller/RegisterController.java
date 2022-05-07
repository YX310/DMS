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

import static com.gxm.dts.util.Constant.*;

@Controller
public class RegisterController {
    @Autowired
    private IndexMapper indexMapper;
    @Autowired(required = false)
    private AdminController adminController;
    @Autowired
    private ProjectController projectController;

    @PostMapping(value = "/user/register")
    public String register(HttpServletRequest request,
                           User user,
                           HttpSession session,
                           Model model) {
        // if has user
        if (indexMapper.checkUser(user.getUsername()) != null) {
            session.setAttribute(Constant.ERROR_INFO, "用户已存在!");

            if (Constant.DEBUG) System.out.println(Constant.ERROR_INFO);
            return "redirect:/register";
        }

        // check email
        if (!Utils.checkEmail(user.getEmail())) {
            session.setAttribute(Constant.ERROR_INFO, "邮箱错误!");
            return "redirect:/register";
        }

        // register
        indexMapper.register(user);
        // jump
        String role = user.getUser_role();
        model.addAttribute(Constant.ERROR_INFO, null);
        session.setAttribute(SESSION_USER_ID, user.getUser_id());
        session.setAttribute(SESSION_USER_NAME, user.getUsername());
        session.setAttribute(SESSION_USER_ROLE,user.getUser_role());
        session.setAttribute(SESSION_USER_EMAIL, user.getEmail());
        session.setAttribute(SESSION_USER_HEAD_IMG, user.getHead_img());
        if (role.equals("管理员")) {
            return adminController.User(request);
        } else {
            if (role.equals("项目经理") || role.equals("开发") || role.equals("测试")) {
                // Now the page we return is the login page, but also can jump to the index page.
                return projectController.Project(request, model);
            }
        }
        session.setAttribute(Constant.ERROR_INFO, "未知错误!");
        return "redirect:/register";
    }
}
