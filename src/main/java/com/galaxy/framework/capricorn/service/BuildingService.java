package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.Building;
import com.galaxy.framework.capricorn.mapper.BuildingMapper;
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
public class BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Building get(String code) {
        return buildingMapper.selectByCode(code);
    }


    public List<Building> findAll(Map<String, Object> search) {
        return buildingMapper.findAll(search);
    }

    public PageInfo<Building> pageInfo(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<Building> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(Building building) {
        if (!StringUtils.isEmpty(building.getCode())) { // 执行更新操作
            Building exist = buildingMapper.selectByCode(building.getCode());
            BeanUtils.copyProperties(building, exist, "id");
            buildingMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(Building.class.getName());
            building.setCode(code);
            buildingMapper.insert(building);
        }
    }

    @Transactional
    public void update(Building building) {
        try {
            buildingMapper.updateByPrimaryKey(building);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
