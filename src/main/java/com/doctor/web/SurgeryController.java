package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.Surgery;
import com.doctor.model.SurgeryOrder;
import com.doctor.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurgeryController {

    @Autowired
    private SurgeryService surgeryService;

    @RequestMapping("/surgery/search")
    public ResultJson search(@RequestParam String orgId) {
        return ResultJson.success(surgeryService.search(orgId));
    }

    @RequestMapping("/surgery/search_doctor")
    public ResultJson searchDoctor(@RequestParam String orgId,
                             @RequestParam String surgeryId) {
        return ResultJson.success(surgeryService.searchDoctor(orgId, surgeryId));
    }

    @RequestMapping("/surgery/order/insert")
    public ResultJson insert(@RequestBody SurgeryOrder order) {
        surgeryService.insert(order);
        return ResultJson.success();
    }

    @RequestMapping("/surgery/order/list")
    public ResultJson listByUser(@RequestParam String userId) {
        return ResultJson.success(surgeryService.listByUser(userId));
    }

    //----------------------------------分割线---------------------------------------
    //admin接口
    @RequestMapping("/admin/surgery/insert")
    public ResultJson physicalInsert(Integer id, String name, String summary, String orgId, String doctorIds) {
        Surgery surgery = new Surgery();
        surgery.setId(id);
        surgery.setName(name);
        surgery.setSummary(summary);
        surgery.setOrgId(orgId);
        surgery.setDoctorIds(doctorIds);
        surgeryService.save(surgery);
        return ResultJson.success();
    }

    @RequestMapping("/admin/surgery/list")
    public ResultJson listAll() {
        return ResultJson.success(surgeryService.listAll());
    }

}
