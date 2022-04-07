package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Defect;

public interface DefectService {
    // 分页查询缺陷列表
    public PageInfo<Defect> selectDefectWithPage(Integer page3, Integer count);

    // 根据缺陷id查询单个缺陷信息
    public Defect selectDefectWithId(String defect_id);

    void addDefect(Defect defect);

    Defect getDefectId(String defect_id);

    // 根据缺陷id修改单个缺陷信息(前台)
    public void updateDefectWithId(Defect defect);

    // 根据缺陷id删除单个缺陷信息
    public void deleteDefectWithId(String defect_id);
}
