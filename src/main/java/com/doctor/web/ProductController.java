package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.Product;
import com.doctor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/list_all")
    public ResultJson list() {
        return ResultJson.success(productService.listAll());
    }


    @RequestMapping("/product/insert")
    public ResultJson insert(@RequestBody Product product) {
//        productService.insert(product);
        return ResultJson.success();
    }

    @RequestMapping("/product/form_insert")
    public ResultJson formInsert(@RequestParam String name,
                                 Integer id,
                                 String summary,
                                 BigDecimal yprice,
                                 BigDecimal price,
                                 MultipartFile logo,
                                 MultipartFile banner,
                                 MultipartFile detail) throws Exception {
        Product product = Product.builder()
                .id(id)
                .name(name)
                .summary(summary)
                .yprice(yprice)
                .price(price)
                .logo(logo != null? logo.getOriginalFilename() : null)
                .banner(banner != null? banner.getOriginalFilename() : null)
                .detail(detail != null? detail.getOriginalFilename() : null)
                .build();
        productService.insertWithFiles(product, logo, banner, detail);
        return ResultJson.success();
    }
}
