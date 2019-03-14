package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.mapper.UserAddressMapper;
import com.doctor.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAddressController {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @RequestMapping("/user/address/update")
    public ResultJson update(@RequestBody UserAddress userAddress) {
        if (userAddress.getId() == null) {
            userAddressMapper.insert(userAddress);
        } else {
            userAddressMapper.updateByPrimaryKeySelective(userAddress);
        }
        return ResultJson.success();
    }

    @RequestMapping("/user/address/list")
    public ResultJson list(@RequestParam String userId) {
        return ResultJson.success(userAddressMapper.selectByUserId(userId));
    }
}
