package com.doctor.service;

import com.doctor.common.Pager;
import com.doctor.mapper.DoctorOrderMapper;
import com.doctor.model.DoctorOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorOrderService {

    @Autowired
    private DoctorOrderMapper orderMapper;

    public void insert(DoctorOrder order) {
        orderMapper.insertSelective(order);
    }

    public List<Map<String, Object>> listByUserId(String userId) {
        return orderMapper.listByUserId(userId);
    }

    public Object listAll(int page, int pageSize) {
        Map params = new HashMap();

        int count = orderMapper.countByParams(params);
        Pager pager = new Pager(page, pageSize, count);

        params.put("start", (page-1) * pageSize);
        params.put("end", page * pageSize);
        List data = orderMapper.listByParams(params);

        pager.setRows(data);
        return pager;
    }
}
