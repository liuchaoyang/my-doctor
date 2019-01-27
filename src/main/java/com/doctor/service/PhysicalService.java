package com.doctor.service;

import com.doctor.mapper.PhysicalOrderMapper;
import com.doctor.mapper.PhysicalTherapyMapper;
import com.doctor.mapper.UserMapper;
import com.doctor.model.PhysicalOrder;
import com.doctor.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhysicalService {


    @Autowired
    private PhysicalTherapyMapper physicalTherapyMapper;
    @Autowired
    private PhysicalOrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    public void insert(PhysicalOrder order) {
        orderMapper.insertSelective(order);
    }

    public List<Map<String, Object>> search(String orgId) {
        return physicalTherapyMapper.searchNameByOrgId(orgId);
    }

    public List<Map<String, Object>> searchDoctor(String orgId, String physicalId) {
        String doctors = physicalTherapyMapper.selectDoctors(physicalId, orgId);
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
}
