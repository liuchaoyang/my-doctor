package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.exception.APIBaseException;
import com.doctor.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付
 */
@RestController
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    @RequestMapping("/wx/pay")
    public ResultJson pay(@RequestParam String userId, @RequestParam String code, @RequestParam int amount) throws APIBaseException {
        return ResultJson.success(wxPayService.pay(userId, code, amount));
    }


}
