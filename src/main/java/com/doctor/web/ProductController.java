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
                                 @RequestParam(required = false, defaultValue = "0") Integer sort,
                                 @RequestParam(required = false) MultipartFile logo,
                                 @RequestParam(required = false) MultipartFile banner,
                                 @RequestParam(value = "details", required = false) MultipartFile[] details) throws Exception {

        String detailArr = "";
        for (MultipartFile detail : details) {
            detailArr += detail.getOriginalFilename();
            detailArr += ",";
        }
        if (detailArr.lastIndexOf(",") != -1) {
            detailArr = detailArr.substring(0, detailArr.length()-1);
        }
        Product product = Product.builder()
                .id(id)
                .name(name)
                .summary(summary)
                .yprice(yprice)
                .price(price)
                .sort(sort)
//                .logo(logo != null? logo.getOriginalFilename() : null)
                .banner(banner != null? banner.getOriginalFilename() : null)
                .detail(detailArr)
                .build();
        productService.insertWithFiles(product, logo, banner, details);
        return ResultJson.success();
    }

    @RequestMapping("/product/detail/upload/{index}")
    public ResultJson upload(@PathVariable int index, int productId,
                             MultipartFile file) throws Exception {
        productService.uploadDetail(productId, index, file);
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

    @RequestMapping("/product/{productId}/detail_file/{fileName}")
    public ResultJson getDetailFile(@PathVariable("productId") int productId,
                                    @PathVariable("fileName") String fileName,
                                    HttpServletResponse response) {
        productService.getProductDetailFile(productId, fileName, response);
        return ResultJson.success();
    }

    @RequestMapping("/order/{productId}/{userId}")
    public ResultJson order(@PathVariable("productId") int productId,
                            @PathVariable("userId") String userId,
                            @RequestParam int count,
                            @RequestParam(required = false) String addrProvince,
                            @RequestParam(required = false) String addrDetail,
                            @RequestParam(required = false) String mobile,
                            @RequestParam(required = false) String userName,
                            @RequestParam BigDecimal pay) {
        return ResultJson.success(productService.order(productId, userId, count, pay, mobile, userName, addrProvince, addrDetail));
    }

    @RequestMapping("/order/{orderId}/confirm")
    public ResultJson orderConfirm(@PathVariable("orderId") int orderId) {
        productService.orderConfirm(orderId);
        return ResultJson.success();
    }

    @RequestMapping("/order/list_by_user")
    public ResultJson orderConfirm(@RequestParam String userId) {
        return ResultJson.success(productService.listByUserId(userId));
    }


    //admin
    @RequestMapping("/admin/product/order/list")
    public ResultJson listByUserId(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResultJson.success(productService.list(page, pageSize));
    }


    @RequestMapping("/admin/product/order/over")
    public ResultJson over(@RequestParam Integer over, @RequestParam Integer orderId) {
        productService.over(orderId, over);
        return ResultJson.success();
    }
}
