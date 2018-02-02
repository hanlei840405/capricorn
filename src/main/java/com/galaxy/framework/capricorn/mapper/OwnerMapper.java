package com.galaxy.framework.capricorn.mapper;

import com.galaxy.framework.capricorn.entity.Owner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OwnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Owner record);

    int insertSelective(Owner record);

    Owner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Owner record);

    int updateByPrimaryKey(Owner record);

    Owner selectByCode(String code);

    List<Owner> findAll(Map<String, Object> params);
}