package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.DictionaryItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictionaryItem record);

    int insertSelective(DictionaryItem record);

    DictionaryItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictionaryItem record);

    int updateByPrimaryKey(DictionaryItem record);

    DictionaryItem selectByCode(String code);

    DictionaryItem selectByKey(String key);

    List<DictionaryItem> findByDictionary(Map<String, Object> params);
}