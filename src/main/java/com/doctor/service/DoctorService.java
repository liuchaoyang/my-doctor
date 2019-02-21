package com.doctor.service;

import com.doctor.exception.APIBaseException;
import com.doctor.exception.UserException;
import com.doctor.mapper.DoctorMapper;
import com.doctor.mapper.UserDoctorChatMapper;
import com.doctor.mapper.UserDoctorRelationMapper;
import com.doctor.mapper.UserMapper;
import com.doctor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private UserDoctorRelationMapper userDoctorRelationMapper;
    @Autowired
    private UserDoctorChatMapper chatMapper;

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
        try {
            userDoctorRelationMapper.insert(relation);
        } catch (Exception e) {
            throw UserException.DOCTOR_BINDED;
        }
    }

    public List<DoctorVO> listByUserID(String userId) {
        return doctorMapper.listByUserId(userId);
    }

    public void chat(String userId, String doctorId, String sendId, String message) {
        UserDoctorChat chat = new UserDoctorChat();
        chat.setUserId(userId);
        chat.setDoctorId(doctorId);
        chat.setSendId(sendId);
        chat.setMessage(message);
        chatMapper.insert(chat);

    }

    public List<UserDoctorChat> chatList(String userId, String doctorId) {
        return chatMapper.list(userId, doctorId);

    }

    public int check(String userId) {
        Doctor doctor = doctorMapper.selectByPrimaryKey(userId);
        return doctor==null?0:1;
    }

    public List<Map<String, Object>> listMyPatient(String doctorId) {
        return doctorMapper.listMyPatient(doctorId);
    }
}
