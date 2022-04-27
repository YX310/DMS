package com.gxm.dts.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.IDefectService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DefectServiceImpl implements IDefectService {

    @Autowired
    private DefectMapper defectMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<Defect> selectDefectWithPage(Integer page, Integer count, Integer id) {
        PageHelper.startPage(page, count);
        List<Defect> defectList = defectMapper.selectDefectWithProjectId(id);
        return new PageInfo<>(defectList);
    }

    @Override
    public Defect selectDefectWithId(String defectId) {
        Defect defect;
        Object object = redisTemplate.opsForValue().get("defect_" + defectId);

        // 检查redis 是否缓存对应 defectID 对象
        if (object != null) {
            defect = (Defect) object;
        } else {
            // 重新获取并保存
            defect = defectMapper.selectDefectWithId(defectId);
            if (defect != null) {
                redisTemplate.opsForValue().set("defect_" + defectId, defect);
            }
        }
        return defect;
    }

    @Override
    public List<Defect> selectDefectWithProjectId(int projectId) {
        return defectMapper.selectDefectWithProjectId(projectId);
    }

    public void addDefect(Defect defect){
        defectMapper.addDefect(defect);
    }

    @Override
    public Defect getDefectId(String defectId) {
        return defectMapper.getDefectId(defectId);
    }

    @Override
    public void updateDefectWithId(Defect defect) {
        defectMapper.updateDefectWithId(defect);
    }

    @Override
    public void deleteDefectWithId(String defectId) {
        defectMapper.deleteDefectWithId(defectId);
    }

    @Override
    public List<DefectProject> selectDesignatedPersonWithUserId(int userId) {
        return defectMapper.selectDesignatedPersonWithUserId(userId);
    }

    @Override
    public List<DefectProject> selectDefectCreatorWithUserId(int userId) {
        return defectMapper.selectDefectCreatorWithUserId(userId);
    }

    @Override
    public DefectProject selectProjectMessageByDefectId(String defectId) {
        return defectMapper.selectProjectMessageByDefectId(defectId);
    }

    @Override
    public List<SearchContent> selectDefect(String keyword) {
        return defectMapper.selectDefect('%' + keyword + '%');
    }
}
