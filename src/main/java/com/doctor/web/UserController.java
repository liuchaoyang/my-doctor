package com.doctor.web;


import com.doctor.common.ResultJson;
import com.doctor.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
                String defaultPath = FILE_DIR_BASE + "head/kefayuan.jpeg";
                //打包成jar以后，实际上文件是存在于jar这个文件里面的资源文件，在磁盘是没有真实路径的（\BOOT-INF\classes!test/test.json ）
                DEFAULT_HEAD_IMG = new File(defaultPath);
                //采用流的方式进行处理，同时读取流时设置编码utf-8
                // TODO
//                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(defaultPath);
//                byte[] bytes = new byte[1024];
//                StringBuilder str = new StringBuilder();
//                try {
//                    while (inputStream.read(bytes) != -1) {
//                        str.append(bytes);
//                    }
//                    DEFAULT_HEAD_IMG = str.toString();
//                } catch (Exception e) {
//                    LOGGER.error("" + e);
//                }


            }

            FileUtils.outputFile(response, DEFAULT_HEAD_IMG);
            return ResultJson.success(0);
        }
        FileUtils.outputFile(response, files[0]);
        return ResultJson.success();
    }
}
