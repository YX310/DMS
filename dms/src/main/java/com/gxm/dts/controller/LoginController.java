package com.gxm.dts.controller;

import com.gxm.dts.mapper.IndexMapper;
import com.gxm.dts.model.domain.User;
import com.gxm.dts.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private IndexMapper indexMapper;
    @Autowired(required = false)
    private AdminController adminController;
    @Autowired
    private ProjectController projectController;

    // 登陆检查
    @RequestMapping(value = "/checkLogin")
    public String checkLogin(HttpServletRequest request,
                             @RequestParam("userId") String userId,
                             @RequestParam("password") String password,
                             @RequestParam("userRole") String userRole,
                             Model model) {
        HttpSession session = request.getSession(true);
        if (userId == null && password == null && userRole == null) {
            return "redirect:/login";
        }
        User user = indexMapper.checkLogin(userId, password, userRole);
        if (user == null) {
            if (Constant.DEBUG) System.out.println("user is null!");
            session.setAttribute(Constant.ERROR_INFO, "用户名或密码错误，请重试！");
            return "redirect:/login";
        } else {
            if (userRole.equals("管理员")
                    ||userRole.equals("项目经理")
                    || userRole.equals("开发")
                    || userRole.equals("测试")) {
                session.setAttribute("userId", user.getUser_id());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userRole",user.getUser_role());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("head_img", user.getHead_img());
                model.addAttribute(Constant.ERROR_INFO, null);
                if (userRole.equals("管理员")) return adminController.User(request);
                else return projectController.Project(request, model);
            } else {
                session.setAttribute(Constant.ERROR_INFO, "角色异常，请重试");
                return "redirect:/login";
            }
        }
    }

}
