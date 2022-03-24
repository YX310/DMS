package com.gxm.dms.mapper;
import com.gxm.dms.model.domain.User;
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
}
