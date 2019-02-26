package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgController {

    @Autowired
    private OrgService orgService;

    @RequestMapping("/org/list_by_parentId")
    public ResultJson list(@RequestParam(value = "parentId", required = false) String parentId,
                           @RequestParam(required = false) Integer type) {
        if (type == null) {
            return ResultJson.success(orgService.listByParentId(parentId));
        } else if (type == 1) {
            return ResultJson.success(orgService.listForPhysical());
        } else if (type == 2) {
            return ResultJson.success(orgService.listForSurgery());
        }
        return ResultJson.failed(500, "无数据");
    }

    @RequestMapping("/org/all")
    public ResultJson list() {
        return ResultJson.success(orgService.listAll());
    }
}
