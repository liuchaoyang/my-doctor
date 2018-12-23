package com.doctor.service;

import com.doctor.mapper.DoctorOrderMapper;
import com.doctor.model.DoctorOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorOrderService {

    @Autowired
    private DoctorOrderMapper orderMapper;

    public void insert(DoctorOrder order) {
        orderMapper.insert(order);
    }

    public List<Map<String, Object>> listByUserId(String userId) {
        return orderMapper.listByUserId(userId);
    }
}
