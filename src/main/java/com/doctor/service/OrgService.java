package com.doctor.service;

import com.doctor.mapper.OrgMapper;
import com.doctor.model.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgService {

    @Autowired
    private OrgMapper orgMapper;


    public List<Org> listByParentId(String parentId) {
        return orgMapper.listByParentId(parentId);
    }
}
