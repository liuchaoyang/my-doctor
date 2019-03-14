package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping("/list")
    public ResultJson listAreas(Integer refId) {
        return ResultJson.success(null == refId || refId < 1 ? areaService.getProvinces() : areaService.getAreasByRefId(refId));
    }
}
