package com.gxm.dms.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.User;
import com.gxm.dms.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired
    private UserServiceImpl userServiceImpl;

//    @GetMapping(value = {"/user_list"})
//    public String toAdminHome(){
//        return "back/user_list";
//    }

    // 用户列表页面（user_list）
    @GetMapping(value = "/user_list")
    String User(HttpServletRequest request) {
        return this.User(request, 1, 5);
    }

    @GetMapping("/page2/{p}")
    public String User(HttpServletRequest request,@PathVariable("p") int page2,
                          @RequestParam(value = "count", defaultValue = "5") int count){
        PageInfo<User> list = userServiceImpl.selectUserWithPage(page2, count);
        request.setAttribute("data2", list);
        request.setAttribute("page2", page2);
        request.setAttribute("count",userServiceImpl.selectUserWithPage(page2, count).getPages());
        return "back/user_list";
    }

    // 用户详情查询
    @GetMapping(value = "/user/{id}")
    public String getUserById(@PathVariable("id") Integer id, HttpServletRequest request){
        User user = userServiceImpl.selectUserWithId(id);
        if(user!=null){
            request.setAttribute("user",user);
            return "client/login";
        }else {
            return "back/user_list";
        }
    }
}
