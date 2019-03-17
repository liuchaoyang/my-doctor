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
    public ResultJson insert(@RequestParam String doctorId,
                           @RequestParam String userId,
                           @RequestParam String userName,
                           @RequestParam String userMobile,
                           @RequestParam String description,
                           @RequestParam Date makeDate,
                           @RequestParam Byte makeType,
                           @RequestParam(required = false) MultipartFile file) {
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

    @RequestMapping("/doctor_order/list_by_user")
    public ResultJson listByUserId(@RequestParam String userId) {
        return ResultJson.success(orderService.listByUserId(userId));
    }

    @RequestMapping("/admin/doctor_order/list_all")
    public ResultJson listByUserId(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResultJson.success(orderService.listAll(page, pageSize));
    }


    @RequestMapping("/admin/doctor_order/over")
    public ResultJson over(@RequestParam Integer over, @RequestParam Integer orderId) {
        orderService.over(orderId, over);
        return ResultJson.success();
    }
}
