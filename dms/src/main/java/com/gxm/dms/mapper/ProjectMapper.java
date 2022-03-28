package com.gxm.dms.mapper;

import com.gxm.dms.model.domain.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.apache.ibatis.annotations.Mapper
public interface ProjectMapper {

    //查找当前登录用户加入的项目
    @Select("SELECT res.username,p.project_name FROM(SELECT u.username,up.project_id FROM USER AS u INNER JOIN user_and_project AS up WHERE u.user_id=up.user_id && u.username=#{userid}) AS res \n" +
            "INNER JOIN project AS p WHERE res.project_id=p.project_id;")
    //public List<Project> selectProjectWithUserId(Integer user_id);
    public List<Project> selectProjectWithUserId();

    // 查询所有的项目信息
    @Select("SELECT * FROM project")
    public List<Project> selectAllProject();


    // 根据项目id查询项目信息
    @Select("SELECT * FROM project WHERE id=#{ project_id}")
    public Project selectProjectWithProject_id(Integer project_id);
}
