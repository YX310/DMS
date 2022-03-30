package com.gxm.dms.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.User;

public interface UserService {

    // 分页查询用户列表
    public PageInfo<User> selectUserWithPage(Integer page2, Integer count);

    // 根据用户id查询单个用户信息
    public User selectUserWithId(Integer user_id);

    // 根据用户id删除单个用户信息
    public void deleteUserWithId(Integer user_id);

    // 根据用户id修改单个用户信息
    public void updateUserWithId(User user);

    User getUserId(Integer user_id);
}
