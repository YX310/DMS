package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DefectServiceImpl implements DefectService {

    @Autowired
    private DefectMapper defectMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<Defect> selectDefectWithPage(Integer page3, Integer count) {
        PageHelper.startPage(page3, count);
        List<Defect> defectList = defectMapper.selectDefectWithPage();
        PageInfo<Defect> pageInfo=new PageInfo<>(defectList);
        return pageInfo;
    }

    @Override
    public Defect selectDefectWithId(String defect_id) {
        Defect defect = null;
        Object o = redisTemplate.opsForValue().get("defect_" + defect_id);
        if(o!=null){
            defect=(Defect)o;
        }else{
            defect = defectMapper.selectDefectWithId(defect_id);
            if(defect!=null){
                redisTemplate.opsForValue().set("defect_" + defect_id,defect);
            }
        }
        return defect;
    }

    public void addDefect(Defect defect){
        int x = defectMapper.addDefect(defect);
        System.out.println("defect: " + defect.getDefect_document());
        System.out.println("x: " + x);
    }

    @Override
    public Defect getDefectId(String defect_id) {
        return defectMapper.getDefectId(defect_id);
    }

    @Override
    public void updateDefectWithId(Defect defect) {
        defectMapper.updateDefectWithId(defect);
    }

    @Override
    public void deleteDefectWithId(String defect_id) {
        defectMapper.deleteDefectWithId(defect_id);
    }
}
