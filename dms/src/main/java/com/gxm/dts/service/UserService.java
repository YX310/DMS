package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.User;

public interface UserService {

    // 分页查询用户列表（后台）
    public PageInfo<User> selectUserWithPage(Integer page2, Integer count);

    // 根据用户id查询单个用户信息（后台）
    public User selectUserWithId(Integer user_id);

    // 根据用户id删除单个用户信息（后台）
    public void deleteUserWithId(Integer user_id);

    // 根据用户id修改单个用户信息(后台)
    public void updateUserWithId(User user);

    User getUserId(Integer user_id);

    // 根据用户id修改单个用户信息(前台)
    public void updateMeWithId(User user);
}
