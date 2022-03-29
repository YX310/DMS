package com.gxm.dms.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.User;

public interface UserService {

    // 分页查询用户列表
    public PageInfo<User> selectUserWithPage(Integer page2, Integer count);

    // 根据用户id查询单个用户信息
    public User selectUserWithId(Integer user_id);
}
