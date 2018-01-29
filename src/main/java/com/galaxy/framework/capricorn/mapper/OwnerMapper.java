package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Owner;

public interface OwnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Owner record);

    int insertSelective(Owner record);

    Owner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Owner record);

    int updateByPrimaryKey(Owner record);
}