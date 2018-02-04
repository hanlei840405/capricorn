package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.House;
import com.galaxy.framework.capricorn.mapper.HouseMapper;
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
public class HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public House get(String code) {
        return houseMapper.selectByCode(code);
    }


    public List<House> findAll(Map<String, Object> search) {
        return houseMapper.findAll(search);
    }

    public PageInfo<House> page(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<House> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(House house) {
        if (!StringUtils.isEmpty(house.getCode())) { // 执行更新操作
            House exist = houseMapper.selectByCode(house.getCode());
            BeanUtils.copyProperties(house, exist, "id");
            houseMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(House.class.getName());
            house.setCode(code);
            houseMapper.insert(house);
        }
    }

    @Transactional
    public void update(House house) {
        try {
            houseMapper.updateByPrimaryKey(house);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int deleteByCode(String code) {
        House house = get(code);
        try {
            house.setStatus("删除");
            return houseMapper.updateByPrimaryKey(house);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuse(String code) {
        House house = get(code);
        try {
            house.setStatus("启用");
            return houseMapper.updateByPrimaryKey(house);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
