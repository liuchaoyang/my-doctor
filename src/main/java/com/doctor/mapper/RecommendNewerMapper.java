package com.doctor.mapper;

import com.doctor.model.RecommendNewer;

public interface RecommendNewerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendNewer record);

    int insertSelective(RecommendNewer record);

    RecommendNewer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendNewer record);

    int updateByPrimaryKey(RecommendNewer record);
}