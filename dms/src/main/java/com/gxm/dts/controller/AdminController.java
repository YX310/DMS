package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.User;
import com.gxm.dts.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.gxm.dts.util.Constant.*;

@Controller
public class AdminController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    // 用户列表页面（userList）
    @GetMapping(value = "/toUserList")
    String User(HttpServletRequest request) {
        return this.User(request, 1, 5);
    }

    @GetMapping("/page2/{p}")
    public String User(HttpServletRequest request,@PathVariable("p") int page2,
                       @RequestParam(value = "count", defaultValue = "5") int count){
        PageInfo<User> list = userServiceImpl.selectUserWithPage(page2, count);
        request.setAttribute("data2", list);
        request.setAttribute("page2", page2);
        request.setAttribute("count", list.getPages());
        return "back/userList";
    }

    // 用户详情查询
    @GetMapping(value = "/user/{id}")
    public String getUserById(@PathVariable("id") Integer id, HttpServletRequest request){
        User user = userServiceImpl.selectUserWithId(id);
        if (user != null) {
            request.setAttribute("user", user);
            return "client/login";
        } else {
            return "back/userList";
        }
    }

    //更新（修改）用户信息
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user",user);
        return "back/userUpdate";
    }

    //修改用户信息
    @RequestMapping("/update")
    public String updateUserWithId(User user){
        userServiceImpl.updateUserWithId(user);
        return "redirect:/toUserList"; //redirect重定向
    }

    //删除用户
    @RequestMapping("/delete")
    public String deleteUser(HttpSession session,
                             Integer id) {
        userServiceImpl.deleteUserWithId(id);
        Object object = session.getAttribute(SESSION_USER_ID);
        if (object != null && id == object) {
            session.setAttribute(SESSION_USER_ID, null);
            session.setAttribute(SESSION_USER_NAME, null);
            session.setAttribute(SESSION_USER_ROLE, null);
            session.setAttribute(SESSION_USER_EMAIL, null);
            session.setAttribute(SESSION_USER_HEAD_IMG, null);
            return "redirect:/login";
        }
        return "redirect:/toUserList"; //redirect重定向
    }

}
