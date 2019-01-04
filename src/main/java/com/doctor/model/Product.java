package com.doctor.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;

    private String name;

    private String summary;

    private BigDecimal yprice;

    private BigDecimal price;

    private byte[] logo;

    private String banner;

    private String detail;

    private Date updateTime;

    private Date createTime;

}