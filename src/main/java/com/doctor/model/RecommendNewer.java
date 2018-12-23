package com.doctor.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class RecommendNewer {
    private Integer id;

    private String userId;

    private String name;

    private String mobile;

    private Date createTime;

}