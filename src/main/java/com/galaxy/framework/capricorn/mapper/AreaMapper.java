package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    Area selectByCode(String code);

    List<Area> findAll(Map<String, Object> params);
}