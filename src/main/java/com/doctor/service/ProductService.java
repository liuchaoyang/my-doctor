package com.doctor.service;

import com.doctor.mapper.BusinessOrderMapper;
import com.doctor.mapper.ProductMapper;
import com.doctor.model.BusinessOrder;
import com.doctor.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BusinessOrderMapper businessOrderMapper;
    @Value("${dir.product}")
    private String FILE_DIR;

    public List<Product> listAll() {
        return productMapper.listAll();
    }

    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertWithFiles(Product product, MultipartFile logo, MultipartFile banner, MultipartFile detail) throws Exception {
        logger.info("Insert product:{}", product);
        if (logo != null) {
            product.setLogo(logo.getBytes());
        }
        if (product.getId() == null) {
            productMapper.insert(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }

        if (banner != null) {
            saveFile(banner, product.getId(), FileCate.BANNER);
        }
        if (detail != null) {
            saveFile(detail, product.getId(), FileCate.DETAIL);
        }
    }

    private void saveFile(MultipartFile multipartFile, int productId, FileCate fileCate) throws Exception {
        BufferedOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(multipartFile.getInputStream());
            byte[] buffer = new byte[1024];

            String filePath = FILE_DIR + productId + "/";
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

    public void getProductLogo(int productId, HttpServletResponse response) {
        Product product = productMapper.selectByPrimaryKey(productId);
        try {
            response.getOutputStream().write(product.getLogo());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getProductBanner(int productId, HttpServletResponse response) {
        Product product = productMapper.selectByPrimaryKey(productId);
        String filePath = FILE_DIR + productId + "/" + product.getBanner();

        outputFile(response, filePath);
    }

    public void getProductDetailFile(int productId, HttpServletResponse response) {
        Product product = productMapper.selectByPrimaryKey(productId);
        String filePath = FILE_DIR + productId + "/" + product.getDetail();

        outputFile(response, filePath);
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

    public int order(int productId, String userId, int count, BigDecimal pay) {
        BusinessOrder order = new BusinessOrder();
        order.setProductId(productId);
        order.setUserId(userId);
        order.setCount(count);
        order.setPay(pay);
        businessOrderMapper.insert(order);
        logger.info("Product order insert:{}", order);
        return order.getId();
    }

    public void orderConfirm(int orderId) {
        BusinessOrder order = businessOrderMapper.selectByPrimaryKey(orderId);
        order.setStatus(1);
        businessOrderMapper.updateByPrimaryKeySelective(order);
        logger.info("Product order confirm:{}", order);
    }

    public List<Map<String,Object>> listByUserId(String userId) {
        return businessOrderMapper.listByUserId(userId);
    }

    enum FileCate {
        LOGO, BANNER, DETAIL
    }


}
