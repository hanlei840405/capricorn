package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.OwnerHouseProperty;

public interface OwnerHousePropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OwnerHouseProperty record);

    int insertSelective(OwnerHouseProperty record);

    OwnerHouseProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OwnerHouseProperty record);

    int updateByPrimaryKey(OwnerHouseProperty record);
}