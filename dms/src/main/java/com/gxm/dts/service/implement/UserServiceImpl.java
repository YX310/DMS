package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.UserMapper;
import com.gxm.dts.model.domain.User;
import com.gxm.dts.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<User> selectUserWithPage(Integer page2, Integer count) {
        PageHelper.startPage(page2, count);
        List<User> userList = userMapper.selectUserWithPage();//需要修改
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public User selectUserWithId(Integer user_id) {
        User user = null;
        Object object = redisTemplate.opsForValue().get("user_" + user_id);
        if (object!=null) {
            user=(User)object;
        } else {
            user = userMapper.selectUserWithId(user_id);
            if (user!=null) {
                redisTemplate.opsForValue().set("user_" + user_id,user);
            }
        }
        return user;
    }

    //删除用户（后台）
    @Override
    public void deleteUserWithId(Integer user_id) {
        userMapper.deleteUserWithId(user_id);
    }

    //修改用户信息（后台）
    @Override
    public void updateUserWithId(User user) {
        userMapper.updateUserWithId(user);
    }

    @Override
    public User getUserId(Integer user_id) {
        return userMapper.getUserId(user_id);
    }

    @Override
    public void updateMeWithId(User user) {
        userMapper.updateMeWithId(user);
    }

    @Override
    public int findUserIdByUsername(String username) {
        return userMapper.findUserIdByUsername(username);
    }

}
