package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Demand;
import com.gxm.dts.model.domain.DemandFile;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IDemandService {
    // 分页查询缺陷列表
    public PageInfo<Demand> selectDemandWithPage(Integer page, Integer count, Integer id);

    // 根据缺陷id查询单个缺陷信息
    public Demand selectDemandWithId(int demandId);

    public Demand getDemandId(int demandId);

    public List<Demand> selectDemandWithProjectId(int projectId);

    //新建需求
    public void addDemand(Demand demand);

    public void addDemandFile(DemandFile demandFile);

    //修改需求信息
    public void updateDemandWithId(Demand demand);

    // 根据id删除需求信息
    public void deleteDemandWithId(int demandId);
}
