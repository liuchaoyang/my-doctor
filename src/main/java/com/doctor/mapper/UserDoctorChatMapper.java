package com.doctor.mapper;

import com.doctor.model.UserDoctorChat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDoctorChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDoctorChat record);

    int insertSelective(UserDoctorChat record);

    UserDoctorChat selectByPrimaryKey(Integer id);

    List<UserDoctorChat> list(@Param("userId") String userId,
                              @Param("doctorId") String doctorId);

    int updateByPrimaryKeySelective(UserDoctorChat record);

    int updateByPrimaryKeyWithBLOBs(UserDoctorChat record);

    int updateByPrimaryKey(UserDoctorChat record);
}