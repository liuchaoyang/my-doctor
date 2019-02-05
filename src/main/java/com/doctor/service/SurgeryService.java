package com.doctor.service;

import com.doctor.mapper.SurgeryMapper;
import com.doctor.mapper.SurgeryOrderMapper;
import com.doctor.mapper.UserMapper;
import com.doctor.model.Surgery;
import com.doctor.model.SurgeryOrder;
import com.doctor.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SurgeryService {


    @Autowired
    private SurgeryMapper surgeryMapper;
    @Autowired
    private SurgeryOrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    public void insert(SurgeryOrder order) {
        orderMapper.insertSelective(order);
    }

    public List<Map<String, Object>> search(String orgId) {
        return surgeryMapper.searchNameByOrgId(orgId);
    }

    public List<Map<String, Object>> searchDoctor(String orgId, String surgeryId) {
        String doctors = surgeryMapper.selectDoctors(surgeryId, orgId);
        if (StringUtils.isBlank(doctors)) {
            return null;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> temp;
        String[] idArray = doctors.split(",");
        for (String doctorId : idArray) {
            User doctor = userMapper.selectByPrimaryKey(doctorId);
            if (doctor != null) {
                temp = new HashMap<>();
                temp.put("doctorId", doctorId);
                temp.put("doctorName", doctor.getName());
                list.add(temp);
            }
        }
        return list;
    }

    public List<Map<String, Object>> listByUser(String userId) {
        return orderMapper.listByUserId(userId);
    }

    public void save(Surgery surgery) {
        if (surgery.getId() == null) {
            surgeryMapper.insertSelective(surgery);
        } else {
            surgeryMapper.updateByPrimaryKeySelective(surgery);
        }
    }

    public List<Map<String, Object>> listAll() {
        return surgeryMapper.listAll();
    }
}
