package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.HouseProperty;
import com.galaxy.framework.capricorn.mapper.HousePropertyMapper;
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
public class HousePropertyService {

    @Autowired
    private HousePropertyMapper housePropertyMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HouseProperty get(String code) {
        return housePropertyMapper.selectByCode(code);
    }


    public List<HouseProperty> findAll(Map<String, Object> search) {
        return housePropertyMapper.findAll(search);
    }

    public PageInfo<HouseProperty> pageInfo(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<HouseProperty> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(HouseProperty houseProperty) {
        if (!StringUtils.isEmpty(houseProperty.getCode())) { // 执行更新操作
            HouseProperty exist = housePropertyMapper.selectByCode(houseProperty.getCode());
            BeanUtils.copyProperties(houseProperty, exist, "id");
            housePropertyMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(HouseProperty.class.getName());
            houseProperty.setCode(code);
            housePropertyMapper.insert(houseProperty);
        }
    }

    @Transactional
    public void update(HouseProperty houseProperty) {
        try {
            housePropertyMapper.updateByPrimaryKey(houseProperty);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
