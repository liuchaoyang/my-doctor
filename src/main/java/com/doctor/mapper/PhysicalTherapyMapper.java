package com.doctor.mapper;

import com.doctor.model.PhysicalTherapy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PhysicalTherapyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalTherapy record);

    int insertSelective(PhysicalTherapy record);

    PhysicalTherapy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhysicalTherapy record);

    int updateByPrimaryKey(PhysicalTherapy record);

    List<Map<String,Object>> searchNameByOrgId(String orgId);

    String selectDoctors(@Param("id") String id,
                         @Param("orgId") String orgId);

    List<Map<String, Object>> listAll();

    List<Map<String, Object>> listOrgs();

}