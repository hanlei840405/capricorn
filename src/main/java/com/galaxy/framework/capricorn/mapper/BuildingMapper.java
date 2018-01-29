package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Building;

public interface BuildingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);
}