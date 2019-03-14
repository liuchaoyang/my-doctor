package com.doctor.mapper;


import com.doctor.model.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AreaMapper {
    List<Area> findProvinces();

    List<Area> findAreasByRefId(Integer refId);

    List<Area> findAreasByIds(List<Integer> areaIds);
}
