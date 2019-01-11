package com.doctor.mapper;

import com.doctor.model.BusinessOrder;

import java.util.List;
import java.util.Map;

public interface BusinessOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessOrder record);

    int insertSelective(BusinessOrder record);

    BusinessOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessOrder record);

    int updateByPrimaryKey(BusinessOrder record);

    List<Map<String, Object>> listByUserId(String userId);
}