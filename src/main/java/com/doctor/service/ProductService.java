package com.doctor.service;

import com.doctor.mapper.ProductMapper;
import com.doctor.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductMapper productMapper;

    public List<Product> listAll() {
        return productMapper.listAll();
    }

    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertWithFiles(Product product, MultipartFile logo, MultipartFile banner, MultipartFile detail) throws Exception {
        if (product.getId() == null) {
            productMapper.insert(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }

        saveFile(logo, product.getId(), FileCate.LOGO);
        saveFile(banner, product.getId(), FileCate.BANNER);
        saveFile(detail, product.getId(), FileCate.DETAIL);
    }

    private void saveFile(MultipartFile multipartFile, int productId, FileCate fileCate) throws Exception {
        BufferedOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(multipartFile.getInputStream());
            byte[] buffer = new byte[1024];

            String filePath = "/Users/liuchaoyang/Documents/java/demo/my-doctor/a/product/" + productId + "/";
//            String filePath = this.getClass().getClassLoader()
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

    enum FileCate {
        LOGO, BANNER, DETAIL
    }


}
