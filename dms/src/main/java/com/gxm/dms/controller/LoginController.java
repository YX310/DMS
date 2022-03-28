package com.gxm.dms.controller;

import com.gxm.dms.mapper.IndexMapper;
import com.gxm.dms.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
                return indexController.toHome(request);
            }else{
                if(user_role.equals("项目经理") || user_role.equals("开发") || user_role.equals("测试")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userid", userid);
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("useremail", user.getEmail());
                    return indexController.toHome(request);
                }
//                }else if (user_role.equals("开发")){
//                    HttpSession session = request.getSession(true);
//                    session.setAttribute("RD_userid", userid);
//                    session.setAttribute("RD_username", user.getUsername());
//                    session.setAttribute("RD_useremail", user.getEmail());
//                    return indexController.toHome();
//                }else if (user_role.equals("测试")){
//                    HttpSession session = request.getSession(true);
//                    session.setAttribute("QA_userid", userid);
//                    session.setAttribute("QA_username", user.getUsername());
//                    session.setAttribute("QA_useremail", user.getEmail());
//                    return indexController.toHome();
//                }
                else{
                    return "client/login";
                }
            }
        }

    }


}
