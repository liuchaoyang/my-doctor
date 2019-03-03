package com.doctor.mapper;

import com.doctor.model.PhysicalOrder;

import java.util.List;
import java.util.Map;

public interface PhysicalOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalOrder record);

    int insertSelective(PhysicalOrder record);

    PhysicalOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhysicalOrder record);

    int updateByPrimaryKey(PhysicalOrder record);

    List<Map<String,Object>> listByUserId(String userId);

    int countByParams(Map params);

    List<Map<String,Object>> listByParams(Map params);
}