package com.doctor.service;

import com.doctor.common.Pager;
import com.doctor.mapper.PhysicalOrderMapper;
import com.doctor.mapper.PhysicalTherapyMapper;
import com.doctor.mapper.UserMapper;
import com.doctor.model.PhysicalOrder;
import com.doctor.model.PhysicalTherapy;
import com.doctor.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhysicalService {
    private static final Logger logger = LoggerFactory.getLogger(PhysicalService.class);

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

    public void save(PhysicalTherapy physicalTherapy) {
        if (physicalTherapy.getId() == null) {
            physicalTherapyMapper.insertSelective(physicalTherapy);
        } else {
            physicalTherapyMapper.updateByPrimaryKeySelective(physicalTherapy);
        }
    }

    public List<Map<String, Object>> listAll() {
        return physicalTherapyMapper.listAll();
    }

    public Object listOrders(int page, int pageSize) {
        Map params = new HashMap();

        int count = orderMapper.countByParams(params);
        Pager pager = new Pager(page, pageSize, count);

        params.put("start", (page-1) * pageSize);
        params.put("end", pageSize);
        List data = orderMapper.listByParams(params);

        pager.setRows(data);
        return pager;
    }

    public void over(Integer orderId, Integer over) {
        PhysicalOrder order = orderMapper.selectByPrimaryKey(orderId);
        order.setOver(over);
        orderMapper.updateByPrimaryKeySelective(order);
        logger.info("PhysicalOrder order over:{}", order);
    }
}
