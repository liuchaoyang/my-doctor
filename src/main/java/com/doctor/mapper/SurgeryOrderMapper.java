package com.doctor.mapper;

import com.doctor.model.SurgeryOrder;

import java.util.List;
import java.util.Map;

public interface SurgeryOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SurgeryOrder record);

    int insertSelective(SurgeryOrder record);

    SurgeryOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SurgeryOrder record);

    int updateByPrimaryKey(SurgeryOrder record);

    List<Map<String,Object>> listByUserId(String userId);

    int countByParams(Map params);

    List<Map<String,Object>> listByParams(Map params);
}