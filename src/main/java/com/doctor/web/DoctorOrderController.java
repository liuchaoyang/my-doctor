package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.DoctorOrder;
import com.doctor.service.DoctorOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

/**
 * 预约挂号
 */
@RestController
public class DoctorOrderController {

    @Autowired
    private DoctorOrderService orderService;

    @RequestMapping("/doctor_order/insert")
    public ResultJson list(@RequestParam String doctorId,
                           @RequestParam String userId,
                           @RequestParam String userName,
                           @RequestParam String userMobile,
                           @RequestParam String description,
                           @RequestParam Date makeDate,
                           @RequestParam Byte makeType,
                           @RequestParam MultipartFile file) {
        DoctorOrder order = DoctorOrder.builder()
                .doctorId(doctorId)
                .userId(userId)
                .userName(userName)
                .userMobile(userMobile)
                .description(description)
                .makeDate(makeDate)
                .makeType(makeType)
                .uploadFiles(file != null? file.getOriginalFilename(): null)
                .build();
        orderService.insert(order);
        return ResultJson.success();
    }
}
