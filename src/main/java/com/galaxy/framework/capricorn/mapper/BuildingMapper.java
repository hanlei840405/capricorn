package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuildingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);

    Building selectByCode(String code);

    List<Building> findAll(Map<String, Object> params);
}