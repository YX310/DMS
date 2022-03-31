package com.gxm.dms.mapper;

import com.gxm.dms.model.domain.Defect;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@org.apache.ibatis.annotations.Mapper
public interface DefectMapper {

    // 缺陷查询
    @Select("SELECT * FROM defect")
    public List<Defect> selectDefectWithPage();

    // 根据id查询缺陷信息
    @Select("SELECT * FROM defect WHERE id=#{defect_id}")
    public Defect selectDefectWithId(Integer defect_id);

    //获取缺陷id
    @Select("SELECT * FROM defect WHERE defect_id = #{defect_id}")
    public Defect getDefectId(Integer defect_id);
}
