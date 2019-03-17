package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.PhysicalOrder;
import com.doctor.model.PhysicalTherapy;
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

    //----------------------------------分割线---------------------------------------
    //admin接口
    @RequestMapping("/admin/physical/insert")
    public ResultJson physicalInsert(Integer id, String name, String summary, String orgId, String doctorIds) {
        PhysicalTherapy physicalTherapy = new PhysicalTherapy();
        physicalTherapy.setId(id);
        physicalTherapy.setName(name);
        physicalTherapy.setSummary(summary);
        physicalTherapy.setOrgId(orgId);
        physicalTherapy.setDoctorIds(doctorIds);
        physicalService.save(physicalTherapy);
        return ResultJson.success();
    }

    @RequestMapping("/admin/physical/list")
    public ResultJson listAll() {
        return ResultJson.success(physicalService.listAll());
    }

    @RequestMapping("/admin/physical/order/list_all")
    public ResultJson listByUserId(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResultJson.success(physicalService.listOrders(page, pageSize));
    }


    @RequestMapping("/admin/physical/order/over")
    public ResultJson over(@RequestParam Integer over, @RequestParam Integer orderId) {
        physicalService.over(orderId, over);
        return ResultJson.success();
    }
}
