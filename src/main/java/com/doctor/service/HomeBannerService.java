package com.doctor.service;

import com.doctor.exception.APIBaseException;
import com.doctor.mapper.HomeBannerMapper;
import com.doctor.model.HomeBanner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class HomeBannerService {
    private static final Logger logger = LoggerFactory.getLogger(HomeBannerService.class);

    @Autowired
    private HomeBannerMapper bannerMapper;
    @Value("${dir.banner}")
    private String FILE_BANNER;

    public void upload(int index, MultipartFile multipartFile) throws Exception {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw APIBaseException.USER_NOT_EXIST;
        }

        String originName = multipartFile.getOriginalFilename();
        BufferedOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        boolean uploadSuccess = false;
        try {
            inputStream = new BufferedInputStream(multipartFile.getInputStream());
            byte[] buffer = new byte[1024];

            String filePath = FILE_BANNER;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
                logger.info("mkdirs:{}", filePath);
            }

            filePath += originName;
            file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
                outputStream.flush();
            }
            uploadSuccess = true;
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

        if (uploadSuccess) {
            //update db
            HomeBanner banner = bannerMapper.selectByPrimaryKey(1);
            switch (index) {
                case 1:
                    banner.setPic1(originName);
                    break;
                case 2:
                    banner.setPic2(originName);
                    break;
                case 3:
                    banner.setPic3(originName);
                    break;
                case 4:
                    banner.setPic4(originName);
                    break;
                case 5:
                    banner.setPic5(originName);
                    break;
            }
            bannerMapper.updateByPrimaryKeySelective(banner);
        }
    }

    public void setEmpty(int index) {
        HomeBanner banner = bannerMapper.selectByPrimaryKey(1);
        switch (index) {
            case 1:
                banner.setPic1(null);
                break;
            case 2:
                banner.setPic2(null);
                break;
            case 3:
                banner.setPic3(null);
                break;
            case 4:
                banner.setPic4(null);
                break;
            case 5:
                banner.setPic5(null);
                break;
        }
        bannerMapper.updateByPrimaryKey(banner);
    }

    public void show(String name, HttpServletResponse response) {

        String filePath = FILE_BANNER + name;

        outputFile(response, filePath);
    }

    public String getBannerFileNames() {
        HomeBanner banner = bannerMapper.selectByPrimaryKey(1);
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(banner.getPic1())) {
            sb.append(banner.getPic1()).append(",");
        }
        if (!StringUtils.isEmpty(banner.getPic2())) {
            sb.append(banner.getPic2()).append(",");
        }
        if (!StringUtils.isEmpty(banner.getPic3())) {
            sb.append(banner.getPic3()).append(",");
        }
        if (!StringUtils.isEmpty(banner.getPic4())) {
            sb.append(banner.getPic4()).append(",");
        }
        if (!StringUtils.isEmpty(banner.getPic5())) {
            sb.append(banner.getPic5()).append(",");
        }
        String files = sb.toString();
        files = files.substring(0, files.lastIndexOf(","));
        return files;
    }

    private void outputFile(HttpServletResponse response, String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[1024];
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            while (fileInputStream.read(buffer) != -1) {
                outputStream.write(buffer);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
