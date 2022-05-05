package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.SearchContent;
import com.gxm.dts.model.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@org.apache.ibatis.annotations.Mapper
public interface UserMapper {

    // 用户查询
    @Select("SELECT * FROM user")
    public List<User> selectUserWithPage();

    // 根据id查询用户信息
    @Select("SELECT * FROM user WHERE id=#{userId}")
    public User selectUserWithId(Integer userId);

    // 查询所有用户名
    @Select("SELECT user_id, username FROM user WHERE username LIKE #{keyword} ORDER BY user_id DESC LIMIT 5")
    public List<SearchContent> selectUser(String keyword);

    // 根据id删除用户信息
    @Select("DELETE FROM user WHERE user_id = #{userId}")
    public void deleteUserWithId(Integer userId);

    //获取用户id
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    public User getUserId(Integer userId);

    //修改用户信息（后台）
    @Select("UPDATE user SET username = #{username}, user_role = #{user_role},user_position = #{user_position},email = #{email} WHERE user_id = #{user_id}")
    public void updateUserWithId(User user);

    //修改用户信息（前台）
    @Select("UPDATE user SET nickname = #{nickname}, password = #{password},head_img = #{head_img}, email = #{email} WHERE user_id = #{user_id}")
    public void updateMeWithId(User user);

    //查询用户id（前台）
    @Select("SELECT user_id FROM user WHERE username = #{username}")
    public Integer findUserIdByUsername(String username);

    //查询用户username（前台）
    @Select("SELECT username FROM user WHERE user_id = #{userId}")
    public String findUsernameById(int userId);
}
