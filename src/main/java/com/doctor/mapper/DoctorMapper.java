package com.doctor.mapper;

import com.doctor.model.Doctor;

public interface DoctorMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);
}