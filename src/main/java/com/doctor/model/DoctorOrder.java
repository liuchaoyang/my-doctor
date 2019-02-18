package com.doctor.model;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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