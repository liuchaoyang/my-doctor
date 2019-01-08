package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.sms.SmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    private SmsClient smsClient;

    @RequestMapping("/sms/verifyCode")
    public ResultJson sendSms(@RequestParam String mobile, @RequestParam("verify_code") String verifyCode) {
        return ResultJson.success(smsClient.sendSmsCode(mobile, verifyCode));
    }
}
