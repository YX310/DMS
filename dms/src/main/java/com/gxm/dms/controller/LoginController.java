package com.gxm.dms.controller;

import com.gxm.dms.mapper.IndexMapper;
import com.gxm.dms.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private IndexMapper mapper;
    @Autowired(required = false)
    private AdminController adminController;
    @Autowired
    private ProjectController projectController;

    // 登陆检查
    @PostMapping(value = "/checkLogin")
    public String checkLogin(HttpServletRequest request,
                             @RequestParam("user_id") String user_id,
                             @RequestParam("passwd") String passwd,
                             @RequestParam("user_role") String user_role) {
        User user = mapper.checkLogin(user_id, passwd, user_role);
        if(user == null){
            return "client/login";
        } else {
            if (user_role.equals("管理员")){
                HttpSession session = request.getSession(true);
                session.setAttribute("user_id", user.getUser_id());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("useremail", user.getEmail());
                return adminController.User(request);
            } else {
                if(user_role.equals("项目经理") || user_role.equals("开发") || user_role.equals("测试")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user_id", user.getUser_id());
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("useremail", user.getEmail());
                    return projectController.Project(request);
                } else {
                    return "client/login";
                }
            }
        }

    }


}
