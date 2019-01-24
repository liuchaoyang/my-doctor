package com.doctor.service;

import com.doctor.mapper.RecommendNewerMapper;
import com.doctor.mapper.UserScoreMapper;
import com.doctor.model.RecommendNewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserScoreService {

    @Autowired
    private UserScoreMapper userScoreMapper;

    public List<Map<String, Object>> listForRecommend(String userId) {
        return userScoreMapper.listForRecommend(userId);
    }

    public List<Map<String, Object>> recommendCount(String userId) {
        return userScoreMapper.recommendCount(userId);
    }
}
