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
    @Autowired
    private IndexController indexController;


    // 登陆检查
    @PostMapping(value = "/checkLogin")
    public String checkLogin(HttpServletRequest request, @RequestParam("userid") String userid, @RequestParam("passwd") String passwd,
                             @RequestParam("user_role") String user_role){
        User user=mapper.checkLogin(userid,passwd,user_role);
        if(user==null){
            return "client/login";
        }else {
            if (user_role.equals("管理员")){
                return indexController.toIndex();
            }else{
                if(user_role.equals("项目经理")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("z_userid", userid);
                    session.setAttribute("z_username", user.getUsername());
                    session.setAttribute("z_useremail", user.getEmail());
                    return indexController.toIndex();
                }else if (user_role.equals("开发")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("y_userid", userid);
                    session.setAttribute("y_username", user.getUsername());
                    session.setAttribute("y_useremail", user.getEmail());
                    return indexController.toIndex();
                }else if (user_role.equals("测试")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("y_userid", userid);
                    session.setAttribute("y_username", user.getUsername());
                    session.setAttribute("y_useremail", user.getEmail());
                    return indexController.toIndex();
                }else{
                    return "client/login";
                }
            }
        }

    }
}
