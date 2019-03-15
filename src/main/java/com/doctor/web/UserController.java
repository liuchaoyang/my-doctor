package com.doctor.web;


import com.doctor.common.ResultJson;
import com.doctor.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
public class UserController {

    @Value("${dir.base}")
    private String FILE_DIR_BASE;

    private static File DEFAULT_HEAD_IMG = null;

    @PostMapping("/user/head/upload")
    public ResultJson uploadHead(MultipartFile file, String userId) throws Exception {
        String filePath = FILE_DIR_BASE + "head/" +  userId + "/";
        FileUtils.deleteFiles(filePath);
        FileUtils.saveFile(file, filePath);
        return ResultJson.success();
    }

    @GetMapping("/user/head/show")
    public ResultJson uploadHead(String userId, HttpServletResponse response) {
        String filePath = FILE_DIR_BASE + "head/" +  userId + "/";
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files == null || files.length < 1) {
            //默认头像
            if(DEFAULT_HEAD_IMG == null) {
                String defaultPath = "static/img/kefayuan.jpeg";
                DEFAULT_HEAD_IMG = new File(this.getClass().getClassLoader().getResource(defaultPath).getPath());
            }

            FileUtils.outputFile(response, DEFAULT_HEAD_IMG);
            return ResultJson.success(0);
        }
        FileUtils.outputFile(response, files[0]);
        return ResultJson.success();
    }
}
