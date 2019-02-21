package com.doctor.mapper;

import com.doctor.model.Doctor;
import com.doctor.model.DoctorVO;

import java.util.List;
import java.util.Map;

public interface DoctorMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Doctor record);

    List<DoctorVO> listByOrgId(String orgId);

    List<DoctorVO> listByUserId(String userId);

    List<Map<String, Object>> listMyPatient(String doctorId);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);
}