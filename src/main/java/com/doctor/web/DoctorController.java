package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.exception.APIBaseException;
import com.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/doctor/list_by_orgId")
    public ResultJson list(@RequestParam String orgId) {
        return ResultJson.success(doctorService.listByOrgId(orgId));
    }

    @RequestMapping("/doctor/bind")
    public ResultJson list(@RequestParam String userId, @RequestParam String doctorId) throws APIBaseException {
        doctorService.bindDoctor(userId, doctorId);
        return ResultJson.success();
    }

    @RequestMapping("/doctor/my_doctor")
    public ResultJson listByUser(@RequestParam String userId) {
        return ResultJson.success(doctorService.listByUserID(userId));
    }

    @RequestMapping("/doctor/chat")
    public ResultJson chat(@RequestParam String userId,
                           @RequestParam String doctorId,
                           @RequestParam String sendId,
                           @RequestParam String message) {
        doctorService.chat(userId, doctorId, sendId, message);
        return ResultJson.success();
    }

    @RequestMapping("/doctor/chat_list")
    public ResultJson chatList(@RequestParam String userId,
                           @RequestParam String doctorId) {
        return ResultJson.success(doctorService.chatList(userId, doctorId));
    }
}
