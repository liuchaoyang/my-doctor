package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.Product;
import com.doctor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        productService.insert(product);
        return ResultJson.success();
    }
}
