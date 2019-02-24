package com.doctor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void deleteFiles(String filePath) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files == null || files.length < 1) {
            return;
        }
        for (File temp : files) {
            logger.info(" delete file:{}", temp.getName());
            temp.delete();
        }
    }


    public static void saveFile(MultipartFile multipartFile, String filePath) throws Exception {
        BufferedOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(multipartFile.getInputStream());
            byte[] buffer = new byte[1024];

            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
                logger.info("mkdirs:{}", filePath);
            }

            filePath += multipartFile.getOriginalFilename();
            file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
                outputStream.flush();
            }
        } catch (Exception e) {
            logger.error("File save failed!", e);
            throw new Exception("File save failed!");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void outputFile(HttpServletResponse response, File file) {

        FileInputStream fileInputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            outputStream = new BufferedOutputStream(response.getOutputStream());
            while (fileInputStream.read(buffer) != -1) {
                outputStream.write(buffer);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
