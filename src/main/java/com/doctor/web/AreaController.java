package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.Area;
import com.doctor.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    private static List<Area> PROVINCES = null;

    @RequestMapping("/list")
    public ResultJson listAreas(Integer refId) {
        return ResultJson.success(null == refId || refId < 1 ? areaService.getProvinces() : areaService.getAreasByRefId(refId));
    }

    @RequestMapping("/all")
    public ResultJson listAll() {
        if (PROVINCES == null) {
            PROVINCES = areaService.getProvinces();
            for (Area province : PROVINCES) {
                List<Area> sons = areaService.getAreasByRefId(province.getAreaId());
                if (sons != null && sons.size() > 0) {
                    for (Area son : sons) {
                        son.setSons(areaService.getAreasByRefId(son.getAreaId()));
                    }
                }

                province.setSons(sons);
            }
        }
        return ResultJson.success(PROVINCES);
    }
}
