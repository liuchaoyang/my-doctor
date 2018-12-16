package com.doctor.service;

import com.doctor.exception.APIBaseException;
import com.doctor.exception.UserException;
import com.doctor.mapper.DoctorMapper;
import com.doctor.mapper.UserDoctorRelationMapper;
import com.doctor.mapper.UserMapper;
import com.doctor.model.Doctor;
import com.doctor.model.DoctorVO;
import com.doctor.model.User;
import com.doctor.model.UserDoctorRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private UserDoctorRelationMapper userDoctorRelationMapper;

    public List<DoctorVO> listByOrgId(String orgId) {
        return doctorMapper.listByOrgId(orgId);
    }

    public void bindDoctor(String userId, String doctorId) throws APIBaseException {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw UserException.USER_NOT_EXIST;
        }
        Doctor doctor = doctorMapper.selectByPrimaryKey(doctorId);
        if (doctor == null) {
            throw UserException.DOCTOR_NOT_EXIST;
        }

        UserDoctorRelation relation = new UserDoctorRelation();
        relation.setUserId(userId);
        relation.setDoctorId(doctorId);
        userDoctorRelationMapper.insert(relation);
    }
}
