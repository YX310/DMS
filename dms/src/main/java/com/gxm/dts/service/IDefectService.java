package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.model.domain.DefectProject;

import java.util.List;

public interface IDefectService {
    // 分页查询缺陷列表
    public PageInfo<Defect> selectDefectWithPage(Integer page3, Integer count);

    // 根据缺陷id查询单个缺陷信息
    public Defect selectDefectWithID(String defectID);

    void addDefect(Defect defect);

    Defect getDefectID(String defectID);

    // 根据缺陷id修改单个缺陷信息(前台)
    public void updateDefectWithID(Defect defect);

    // 根据缺陷id删除单个缺陷信息
    public void deleteDefectWithID(String defectID);

    //根据当前用户id查找被指派的任务
    public List<DefectProject> selectDesignatedPersonWithUserID(int user_id);

    //根据当前用户id查找当前用户报告的任务
    public List<DefectProject> selectDefectCreatorWithUserID(int user_id);
}
