package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

    Dictionary selectByCode(String code);

    Dictionary selectByKey(String key);

    List<Dictionary> findAll(Map<String, Object> params);
}