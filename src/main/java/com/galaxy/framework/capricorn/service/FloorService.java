package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.Floor;
import com.galaxy.framework.capricorn.mapper.FloorMapper;
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
public class FloorService {

    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Floor get(String code) {
        return floorMapper.selectByCode(code);
    }


    public List<Floor> findAll(Map<String, Object> search) {
        return floorMapper.findAll(search);
    }

    public PageInfo<Floor> pageInfo(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<Floor> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(Floor floor) {
        if (!StringUtils.isEmpty(floor.getCode())) { // 执行更新操作
            Floor exist = floorMapper.selectByCode(floor.getCode());
            BeanUtils.copyProperties(floor, exist, "id");
            floorMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(Floor.class.getName());
            floor.setCode(code);
            floorMapper.insert(floor);
        }
    }

    @Transactional
    public void update(Floor floor) {
        try {
            floorMapper.updateByPrimaryKey(floor);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
