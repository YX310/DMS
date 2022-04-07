package com.gxm.dts.mapper;
import com.gxm.dts.model.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @类名 IndexMapper
 * @描述 定义了一个相关数据库查询操作接口
 * @时间 2023-3
 */

@Component
@org.apache.ibatis.annotations.Mapper
public interface IndexMapper {
    // 登录
    @Select("select* from user where username=#{user_id} and password=#{passwd} and user_role=#{user_role}")
    public User checkLogin(String user_id, String passwd, String user_role);

    //注册
    @Insert("insert into user values(#{user_id},#{username},#{nickname},#{password},#{user_role},#{head_img},#{company}," +
            "#{user_position},#{employee_number},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "user_id",keyColumn = "user_id")
    public void register(User user);

    // 检索重复username
    @Select("select* from user where username=#{user_id}")
    public User checkUser(String user_id);
}
