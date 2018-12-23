package com.doctor.mapper;

import com.doctor.model.DoctorOrder;

public interface DoctorOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DoctorOrder record);

    int insertSelective(DoctorOrder record);

    DoctorOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DoctorOrder record);

    int updateByPrimaryKey(DoctorOrder record);
}