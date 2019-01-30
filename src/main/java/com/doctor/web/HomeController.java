package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.service.HomeBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeBannerService bannerService;

    @RequestMapping("/index")
    public String index() {
        return "hello, man!";
    }

    @RequestMapping("/home/banner/list")
    public ResultJson show() throws IOException {
        return ResultJson.success(bannerService.getBannerFileNames());
    }

    @RequestMapping("/home/banner/show")
    public ResultJson show(@RequestParam String name, HttpServletResponse response) throws IOException {
        bannerService.show(name, response);
        return ResultJson.success();
    }

    @RequestMapping("/home/banner/upload/{index}")
    public ResultJson upload(@PathVariable int index,
                       MultipartFile file) throws Exception {
        bannerService.upload(index, file);
        return ResultJson.success();
    }




}
