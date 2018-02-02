package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Floor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FloorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Floor record);

    int insertSelective(Floor record);

    Floor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);

    Floor selectByCode(String code);

    List<Floor> findAll(Map<String, Object> params);
}