package com.gxm.dts.service;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    //查找当前需求所属项目的信息
    public DemandProject selectProjectMessageByDemandId(Integer demandId);

    // 查询所有需求标题
    public List<SearchContent> selectDemand(String keyword);
}
