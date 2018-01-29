package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Floor;

public interface FloorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Floor record);

    int insertSelective(Floor record);

    Floor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);
}