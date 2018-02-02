package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.HouseProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HousePropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseProperty record);

    int insertSelective(HouseProperty record);

    HouseProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseProperty record);

    int updateByPrimaryKey(HouseProperty record);

    HouseProperty selectByCode(String code);

    List<HouseProperty> findAll(Map<String, Object> params);
}