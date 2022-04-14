package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.model.domain.DefectFile;
import com.gxm.dts.service.IDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefectServiceImpl implements IDefectService {

    @Autowired
    private DefectMapper defectMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<Defect> selectDefectWithPage(Integer page, Integer count) {
        PageHelper.startPage(page, count);
        List<Defect> defectList = defectMapper.selectDefectWithPage();
        return new PageInfo<>(defectList);
    }

    @Override
    public Defect selectDefectWithID(String defectID) {
        Defect defect;
        Object object = redisTemplate.opsForValue().get("defect_" + defectID);

        // 检查redis 是否缓存对应 defectID 对象
        if (object != null) {
            defect = (Defect) object;
        } else {
            // 重新获取并保存
            defect = defectMapper.selectDefectWithID(defectID);
            if (defect != null) {
                redisTemplate.opsForValue().set("defect_" + defectID, defect);
            }
        }
        return defect;
    }

    public void addDefect(Defect defect){
        defectMapper.addDefect(defect);
    }

    public void addDefectFile(DefectFile defectFile){
        defectMapper.addDefectFile(defectFile);
    }

    @Override
    public Defect getDefectID(String defectID) {
        return defectMapper.getDefectID(defectID);
    }

    @Override
    public void updateDefectWithID(Defect defect) {
        defectMapper.updateDefectWithID(defect);
    }

    @Override
    public void deleteDefectWithID(String defectID) {
        defectMapper.deleteDefectWithID(defectID);
    }
}
