package com.doctor.service;

import com.doctor.mapper.OrgMapper;
import com.doctor.model.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrgService {

    @Autowired
    private OrgMapper orgMapper;


    public List<Org> listByParentId(String parentId) {
        return orgMapper.listByParentId(parentId);
    }

    public Object listAll() {
        List<Org> root = orgMapper.listByParentId(null);
        List<Map<String, Object>> result = new ArrayList();
        Map<String, Object> level1;
        for (Org org : root) {
            level1 = new HashMap<>();
            level1.put("id", org.getId());
            level1.put("name", org.getOrgname());

            List<Org> two = orgMapper.listByParentId(org.getId());
            if (two != null && two.size() > 0) {
                List<Map<String, Object>> levelTwoList = new ArrayList();

                for (Org orgTwo : two) {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("id", orgTwo.getId());
                    temp.put("name", orgTwo.getOrgname());
                    levelTwoList.add(temp);
                }
                level1.put("child", levelTwoList);
            }
            result.add(level1);
        }
        return result;
    }
}
