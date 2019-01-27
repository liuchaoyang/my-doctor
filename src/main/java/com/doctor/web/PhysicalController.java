package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.PhysicalOrder;
import com.doctor.service.PhysicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhysicalController {

    @Autowired
    private PhysicalService physicalService;

    @RequestMapping("/physical/search")
    public ResultJson search(@RequestParam String orgId) {
        return ResultJson.success(physicalService.search(orgId));
    }

    @RequestMapping("/physical/search_doctor")
    public ResultJson searchDoctor(@RequestParam String orgId,
                             @RequestParam String physicalId) {
        return ResultJson.success(physicalService.searchDoctor(orgId, physicalId));
    }

    @RequestMapping("/physical/order/insert")
    public ResultJson insert(@RequestBody PhysicalOrder order) {
        physicalService.insert(order);
        return ResultJson.success();
    }

    @RequestMapping("/physical/order/list")
    public ResultJson listByUser(@RequestParam String userId) {
        return ResultJson.success(physicalService.listByUser(userId));
    }

}
