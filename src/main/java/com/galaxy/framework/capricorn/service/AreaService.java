package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.Area;
import com.galaxy.framework.capricorn.mapper.AreaMapper;
import com.galaxy.framework.pisces.exception.db.UpdateException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Area get(String code) {
        return areaMapper.selectByCode(code);
    }


    public List<Area> findAll(Map<String, Object> search) {
        return areaMapper.findAll(search);
    }

    public PageInfo<Area> pageInfo(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<Area> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(Area area) {
        if (!StringUtils.isEmpty(area.getCode())) { // 执行更新操作
            Area exist = areaMapper.selectByCode(area.getCode());
            BeanUtils.copyProperties(area, exist, "id");
            areaMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(Area.class.getName());
            area.setCode(code);
            areaMapper.insert(area);
        }
    }

    @Transactional
    public void update(Area area) {
        try {
            areaMapper.updateByPrimaryKey(area);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
