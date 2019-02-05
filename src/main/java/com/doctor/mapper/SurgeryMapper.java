package com.doctor.mapper;

import com.doctor.model.Surgery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurgeryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Surgery record);

    int insertSelective(Surgery record);

    Surgery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Surgery record);

    int updateByPrimaryKey(Surgery record);

    List<Map<String,Object>> searchNameByOrgId(String orgId);

    String selectDoctors(@Param("id") String id,
                         @Param("orgId") String orgId);

    List<Map<String,Object>> listAll();

}