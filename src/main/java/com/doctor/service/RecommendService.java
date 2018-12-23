package com.doctor.service;

import com.doctor.mapper.RecommendNewerMapper;
import com.doctor.model.RecommendNewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendService {

    @Autowired
    private RecommendNewerMapper recommendNewerMapper;
    public void insert(RecommendNewer newer) {
        recommendNewerMapper.insert(newer);
    }
}
