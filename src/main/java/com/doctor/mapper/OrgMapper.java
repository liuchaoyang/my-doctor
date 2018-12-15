package com.doctor.mapper;

import com.doctor.model.Org;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgMapper {
    int deleteByPrimaryKey(String id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);

    List<Org> listByParentId(@Param("parentId") String parentId);
}