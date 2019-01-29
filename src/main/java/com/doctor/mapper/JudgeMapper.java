package com.doctor.mapper;

import com.doctor.model.Judge;

public interface JudgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Judge record);

    int insertSelective(Judge record);

    Judge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Judge record);

    int updateByPrimaryKey(Judge record);
}