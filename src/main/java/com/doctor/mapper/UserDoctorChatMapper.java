package com.doctor.mapper;

import com.doctor.model.UserDoctorChat;

public interface UserDoctorChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDoctorChat record);

    int insertSelective(UserDoctorChat record);

    UserDoctorChat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDoctorChat record);

    int updateByPrimaryKeyWithBLOBs(UserDoctorChat record);

    int updateByPrimaryKey(UserDoctorChat record);
}