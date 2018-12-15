package com.doctor.service;

import com.doctor.mapper.DoctorMapper;
import com.doctor.model.DoctorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    public List<DoctorVO> listByOrgId(String orgId) {
        return doctorMapper.listByOrgId(orgId);
    }
}
