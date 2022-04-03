package com.gxm.dms.mapper;

import com.gxm.dms.model.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.apache.ibatis.annotations.Mapper
public interface UserMapper {

    // 用户查询
    @Select("SELECT * FROM user")
    public List<User> selectUserWithPage();

    // 根据id查询用户信息
    @Select("SELECT * FROM user WHERE id=#{user_id}")
    public User selectUserWithId(Integer user_id);

    // 根据id删除用户信息
    @Select("DELETE FROM user WHERE user_id = #{user_id}")
    public void deleteUserWithId(Integer user_id);

    //获取用户id
    @Select("SELECT * FROM user WHERE user_id = #{user_id}")
    public User getUserId(Integer user_id);

    //修改用户信息（后台）
    @Select("UPDATE user SET username = #{username}, user_role = #{user_role},user_position = #{user_position},email = #{email} WHERE user_id = #{user_id}")
    public void updateUserWithId(User user);

    //修改用户信息（前台）
    @Select("UPDATE user SET username = #{username}, user_position = #{user_position},employee_number = #{employee_number},email = #{email} WHERE user_id = #{user_id}")
    public void updateMeWithId(User user);

}
