package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Defect;

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
}
