package com.doctor.mapper;

import com.doctor.model.DoctorOrder;

import java.util.List;
import java.util.Map;

public interface DoctorOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DoctorOrder record);

    int insertSelective(DoctorOrder record);

    DoctorOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DoctorOrder record);

    int updateByPrimaryKey(DoctorOrder record);

    List<Map<String,Object>> listByUserId(String userId);

    List<Map<String,Object>> listAll();

    int countByParams(Map params);

    List<Map<String,Object>> listByParams(Map params);
}