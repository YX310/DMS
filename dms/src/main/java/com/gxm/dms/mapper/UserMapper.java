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

    // 根据id用户信息
    @Select("SELECT * FROM user WHERE id=#{user_id}")
    public User selectUserWithId(Integer user_id);

}
