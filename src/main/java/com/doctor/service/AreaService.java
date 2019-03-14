package com.doctor.service;

import com.doctor.mapper.AreaMapper;
import com.doctor.model.Area;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaMapper areaMapper;

    public List<Area> getProvinces() {
        return areaMapper.findProvinces();
    }

    public List<Area> getAreasByRefId(Integer refId) {
        if (null == refId) {
            return Collections.emptyList();
        }

        return areaMapper.findAreasByRefId(refId);
    }

    public List<Area> getAreasByIds(List<Integer> areaIds) {
        if (CollectionUtils.isEmpty(areaIds)) {
            return Collections.emptyList();
        }

        return areaMapper.findAreasByIds(areaIds);
    }
}
