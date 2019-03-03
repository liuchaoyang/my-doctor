package com.doctor.service;

import com.doctor.common.Pager;
import com.doctor.exception.APIBaseException;
import com.doctor.exception.UserException;
import com.doctor.mapper.RecommendNewerMapper;
import com.doctor.mapper.UserMapper;
import com.doctor.mapper.UserScoreMapper;
import com.doctor.model.RecommendNewer;
import com.doctor.model.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendService {

    @Autowired
    private RecommendNewerMapper recommendNewerMapper;
    @Autowired
    private UserScoreMapper userScoreMapper;
    @Autowired
    private UserMapper userMapper;


    @Transactional
    public void insert(RecommendNewer newer) throws APIBaseException {
        int existed = userMapper.existedMobile(newer.getMobile());
        if (existed > 0) {
            throw UserException.USER_EXIST;
        }
        try {
            recommendNewerMapper.insert(newer);
        } catch (Exception e) {
            throw UserException.USER_EXIST;
        }
        UserScore score = new UserScore();
        score.setUserId(newer.getUserId());
        score.setRefUserId(String.valueOf(newer.getId()));
        userScoreMapper.insertSelective(score);
    }

    public Object list(int page, int pageSize) {
        Map params = new HashMap();

        int count = userScoreMapper.countByParams(params);
        Pager pager = new Pager(page, pageSize, count);

        params.put("start", (page-1) * pageSize);
        params.put("end", page * pageSize);
        List data = userScoreMapper.listByParams(params);

        pager.setRows(data);
        return pager;
    }
}
