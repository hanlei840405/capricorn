package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.Owner;
import com.galaxy.framework.capricorn.mapper.OwnerMapper;
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
public class OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Owner selectByCode(String code) {
        return ownerMapper.selectByCode(code);
    }


    public List<Owner> findAll(Map<String, Object> search) {
        return ownerMapper.findAll(search);
    }

    public PageInfo<Owner> page(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<Owner> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAll(search));
        return pageInfo;
    }

    @Transactional
    public void save(Owner owner) {
        if (!StringUtils.isEmpty(owner.getCode())) { // 执行更新操作
            Owner exist = ownerMapper.selectByCode(owner.getCode());
            BeanUtils.copyProperties(owner, exist, "id");
            ownerMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(Owner.class.getName());
            owner.setCode(code);
            ownerMapper.insert(owner);
        }
    }

    @Transactional
    public void update(Owner owner) {
        try {
            ownerMapper.updateByPrimaryKey(owner);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int deleteByCode(String code) {
        Owner owner = selectByCode(code);
        try {
            owner.setStatus("删除");
            return ownerMapper.updateByPrimaryKey(owner);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuse(String code) {
        Owner owner = selectByCode(code);
        try {
            owner.setStatus("启用");
            return ownerMapper.updateByPrimaryKey(owner);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
