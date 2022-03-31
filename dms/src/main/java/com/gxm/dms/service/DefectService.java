package com.gxm.dms.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dms.model.domain.Defect;
import com.gxm.dms.model.domain.User;

public interface DefectService {
    // 分页查询缺陷列表
    public PageInfo<Defect> selectDefectWithPage(Integer page3, Integer count);

    // 根据缺陷id查询单个缺陷信息
    public Defect selectDefectWithId(Integer defect_id);
}
