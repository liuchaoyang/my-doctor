package com.doctor.mapper;

import com.doctor.model.BusinessOrder;

public interface BusinessOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessOrder record);

    int insertSelective(BusinessOrder record);

    BusinessOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessOrder record);

    int updateByPrimaryKey(BusinessOrder record);
}