package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.HouseProperty;

public interface HousePropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseProperty record);

    int insertSelective(HouseProperty record);

    HouseProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseProperty record);

    int updateByPrimaryKey(HouseProperty record);
}