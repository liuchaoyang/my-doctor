package com.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@Builder
public class DoctorOrder {
    private Integer id;

    private String doctorId;

    private String userId;

    private String userName;

    private String userMobile;

    private String description;

    private String uploadFiles;

    private Date makeDate;

    private Integer status;

    private Byte makeType;

    private Date createTime;

}