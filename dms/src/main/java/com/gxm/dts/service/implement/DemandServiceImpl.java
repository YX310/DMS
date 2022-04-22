package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DemandMapper;
import com.gxm.dts.model.domain.Demand;
import com.gxm.dts.model.domain.DemandFile;
import com.gxm.dts.model.domain.UpdateDemand;
import com.gxm.dts.service.IDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DemandServiceImpl implements IDemandService {
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<Demand> selectDemandWithPage(Integer page, Integer count, Integer id) {
        PageHelper.startPage(page, count);
        List<Demand> demandList = demandMapper.selectDemandWithProjectId(id);
        return new PageInfo<>(demandList);
    }

    @Override
    public Demand selectDemandWithId(int demandId) {
        Demand demand;
        Object object = redisTemplate.opsForValue().get("demand_" + demandId);

        // 检查redis 是否缓存对应 demandId 对象
        if (object != null) {
            demand = (Demand) object;
        } else {
            // 重新获取并保存
            demand = demandMapper.selectDemandWithId(demandId);
            if (demand != null) {
                redisTemplate.opsForValue().set("demand_" + demandId, demand);
            }
        }
        return demand;
    }

    @Override
    public Demand getDemandId(int demandId) {
        return demandMapper.getDemandId(demandId);
    }

    @Override
    public List<Demand> selectDemandWithProjectId(int projectId) {
        return demandMapper.selectDemandWithProjectId(projectId);
    }

    @Override
    public void addDemand(Demand demand) {
        demandMapper.addDemand(demand);
    }

    @Override
    public void addDemandFile(DemandFile demandFile) {
        demandMapper.addDemandFile(demandFile);
    }

    @Override
    public void updateDemandWithId(Demand demand) {
        demandMapper.updateDemandWithId(demand);
    }

    @Override
    public void deleteDemandWithId(int demandId) {
        demandMapper.deleteDemandWithId(demandId);
    }

    @Override
    public List<UpdateDemand> selectUpdateDemandWithDemandId(int demandId) {
        return demandMapper.selectUpdateDemandWithDemandId(demandId);
    }

    @Override
    public void addUpdateDemand(UpdateDemand updateDemand) {
        demandMapper.addUpdateDemand(updateDemand);
    }

    @Override
    public Map<String, Integer> selectDemand() {
        return demandMapper.selectDemand();
    }


}
