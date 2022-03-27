package com.gxm.dms.controller;

import com.gxm.dms.mapper.IndexMapper;
import com.gxm.dms.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @Autowired
    private IndexMapper mapper;
    @Autowired
    private IndexController indexController;

    @PostMapping(value = "/user/register")
    public String register(HttpSession session, User user) {
        // if has user
        if (mapper.checkUser(user.getUsername()) != null) return indexController.toHome();

        // register
        mapper.register(user);
        // jump
        String role = user.getUser_role();
        if (role.equals("管理员")) {
            return indexController.toHome();
        } else {

            if (role.equals("项目经理") || role.equals("开发") || role.equals("测试")) {
                session.setAttribute("username", user.getUsername());
                session.setAttribute("useremail", user.getEmail());
                // Now the page we return is the login page, but also can jump to the index page.
                return indexController.toHome();
            }
        }
        return indexController.toRegister();
    }
}
