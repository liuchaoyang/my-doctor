package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.Product;
import com.doctor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
//                .logo(logo != null? logo.getOriginalFilename() : null)
                .banner(banner != null? banner.getOriginalFilename() : null)
                .detail(detail != null? detail.getOriginalFilename() : null)
                .build();
        productService.insertWithFiles(product, logo, banner, detail);
        return ResultJson.success();
    }

    @RequestMapping("/product/{productId}/logo")
    public ResultJson getImage(@PathVariable("productId") int productId, HttpServletResponse response) {
        productService.getProductLogo(productId, response);
        return ResultJson.success();
    }

    @RequestMapping("/product/{productId}/banner_file")
    public ResultJson getBannerFile(@PathVariable("productId") int productId, HttpServletResponse response) {
        productService.getProductBanner(productId, response);
        return ResultJson.success();
    }

    @RequestMapping("/product/{productId}/detail_file")
    public ResultJson getDetailFile(@PathVariable("productId") int productId, HttpServletResponse response) {
        productService.getProductDetailFile(productId, response);
        return ResultJson.success();
    }
}
