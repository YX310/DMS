package com.gxm.dms.mapper;
import com.gxm.dms.model.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @类名 IndexMapper
 * @描述 定义了一个相关数据库查询操作接口
 * @时间 2023-3
 * @创建者  官晓敏
 */

@Component
@org.apache.ibatis.annotations.Mapper
public interface IndexMapper {
    // 登陆
    @Select("select* from user where username=#{userid} and password=#{passwd} and user_role=#{user_role}")
    public User checkLogin(String userid, String passwd, String user_role);

    //注册
    @Insert("insert into user values(#{user_id},#{username},#{nickname},#{password},#{user_role},#{head_img},#{company}," +
            "#{position},#{employee_number},#{email})")
    @Options(useGeneratedKeys = true,keyProperty = "user_id",keyColumn = "user_id")
    public void register(User user);

    // 检索重复username
    @Select("select* from user where username=#{userid}")
    public User checkUser(String userid);
}
