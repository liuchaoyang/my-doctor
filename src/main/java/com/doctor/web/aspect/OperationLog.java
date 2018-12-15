package com.doctor.web.aspect;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * method操作的名称
     */
    String operationName();


}
