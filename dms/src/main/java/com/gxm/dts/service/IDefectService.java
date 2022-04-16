package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.model.domain.DefectProject;

import java.util.List;

public interface IDefectService {
    // 分页查询缺陷列表
    public PageInfo<Defect> selectDefectWithPage(Integer page, Integer count, Integer id);

    // 根据缺陷id查询单个缺陷信息
    public Defect selectDefectWithId(String defectId);

    void addDefect(Defect defect);

    Defect getDefectId(String defectId);

    // 根据缺陷id修改单个缺陷信息(前台)
    public void updateDefectWithId(Defect defect);

    // 根据缺陷id删除单个缺陷信息
    public void deleteDefectWithId(String defectId);

    //根据当前用户id查找被指派的任务
    public List<DefectProject> selectDesignatedPersonWithUserId(int userId);

    //根据当前用户id查找当前用户报告的任务
    public List<DefectProject> selectDefectCreatorWithUserId(int userId);
}
