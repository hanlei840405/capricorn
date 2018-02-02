package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.OwnerHouseProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OwnerHousePropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OwnerHouseProperty record);

    int insertSelective(OwnerHouseProperty record);

    OwnerHouseProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OwnerHouseProperty record);

    int updateByPrimaryKey(OwnerHouseProperty record);

    List<OwnerHouseProperty> findAll(Map<String, Object> params);
}