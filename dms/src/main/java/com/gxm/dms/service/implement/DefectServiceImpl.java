package com.gxm.dms.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dms.mapper.DefectMapper;
import com.gxm.dms.model.domain.Defect;
import com.gxm.dms.service.DefectService;
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
        defect.setDefect_id(UUID.randomUUID().toString().toUpperCase());
        defectMapper.addDefect(defect);
    }
}
