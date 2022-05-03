package com.gxm.dts.controller;

import com.gxm.dts.mapper.IndexMapper;
import com.gxm.dts.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PostMapping(value = "/checkLogin")
    public String checkLogin(HttpServletRequest request,
                             @RequestParam("userId") String userId,
                             @RequestParam("password") String password,
                             @RequestParam("userRole") String userRole) {
        User user = indexMapper.checkLogin(userId, password, userRole);
        if (user == null) {
            return "client/login";
        } else {
            if (userRole.equals("管理员")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", user.getUser_id());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userRole",user.getUser_role());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("head_img", user.getHead_img());
                return adminController.User(request);
            } else {
                if (userRole.equals("项目经理") || userRole.equals("开发") || userRole.equals("测试")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userId", user.getUser_id());
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("userRole",user.getUser_role());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("head_img", user.getHead_img());
                    return projectController.Project(request);
                } else {
                    return "client/login";
                }
            }
        }
    }

}
