package com.doctor.mapper;

import com.doctor.model.UserDoctorRelation;

public interface UserDoctorRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDoctorRelation record);

    int insertSelective(UserDoctorRelation record);

    UserDoctorRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDoctorRelation record);

    int updateByPrimaryKeyWithBLOBs(UserDoctorRelation record);

    int updateByPrimaryKey(UserDoctorRelation record);
}