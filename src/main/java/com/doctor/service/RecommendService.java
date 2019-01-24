package com.doctor.service;

import com.doctor.mapper.RecommendNewerMapper;
import com.doctor.mapper.UserScoreMapper;
import com.doctor.model.RecommendNewer;
import com.doctor.model.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendService {

    @Autowired
    private RecommendNewerMapper recommendNewerMapper;
    @Autowired
    private UserScoreMapper userScoreMapper;


    @Transactional
    public void insert(RecommendNewer newer) {
        recommendNewerMapper.insert(newer);

        UserScore score = new UserScore();
        score.setUserId(newer.getUserId());
        score.setRefUserId(String.valueOf(newer.getId()));
        userScoreMapper.insertSelective(score);
    }
}
