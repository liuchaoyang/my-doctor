package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.exception.APIBaseException;
import com.doctor.util.EnvUtils;
import com.doctor.util.ExportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private EnvUtils envUtils;

    @RequestMapping("/index")
    public String index() {
        return "hello, man!";
    }

    @RequestMapping("/user/export")
    public void export(HttpServletResponse response) throws IOException {
        ExportExcel.export(response);
    }


    @RequestMapping("/test/active_profile")
    public Object activeProfile() throws IOException, APIBaseException {
        return ResultJson.success(envUtils.activeEnv());
    }

    @PostMapping("/test/post")
    public Object post(@RequestParam String orderIds,
                       @RequestParam int type) {
        System.out.println(orderIds);
        return ResultJson.success();
    }


    @GetMapping("/test/301")
    public Object test301(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://www.baidu.com");

        return ResultJson.success();
    }


}
