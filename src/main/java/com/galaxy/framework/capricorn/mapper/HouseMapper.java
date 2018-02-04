package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.House;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    House selectByCode(String code);

    List<House> findAll(Map<String, Object> params);
}