package com.doctor.mapper;

import com.doctor.model.UserScore;

import java.util.List;
import java.util.Map;

public interface UserScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserScore record);

    int insertSelective(UserScore record);

    UserScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserScore record);

    int updateByPrimaryKey(UserScore record);

    List<Map<String,Object>> listForRecommend(String userId);

    List<Map<String,Object>> recommendCount(String userId);

    int countByParams(Map params);

    List<Map<String,Object>> listByParams(Map params);
}