package com.doctor.web.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class OperationLogAspectj {

    @Autowired
//    UserOperationLogMapper userOperationLogMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogAspectj.class);


    private String accountNo = null;
    private String appId = null;
    private String subAppId = null;

    /**
     * 方法调用前触发 读取请求参数
     */
    @Before(value = "@annotation(com.doctor.web.aspect.OperationLog)")
    public void before(JoinPoint joinPoint) {
    }

    /**
     * 方法调用后触发
     */
    @After(value = "@annotation(com.doctor.web.aspect.OperationLog)")
    public void after(JoinPoint joinPoint) throws IOException {
        try {
            HttpServletRequest request = getHttpServletRequest();
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = null;
            try {
                targetClass = Class.forName(targetName);
            } catch (ClassNotFoundException e) {
                LOGGER.error("OperationLogAspectj targetClass Error", e);
            }
            Method[] methods = targetClass.getMethods();
            Integer bodyIndex = null; //body注解序号
            String operationName = null;
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs != null && clazzs.length == arguments.length && method.getAnnotation(OperationLog.class) != null) {
                        operationName = method.getAnnotation(OperationLog.class).operationName();

                        Parameter[] parameters = method.getParameters();
                        for (int i = 0; i < parameters.length; i++) {
                            if (parameters[i].getDeclaredAnnotations().length > 0 &&
                                    parameters[i].getDeclaredAnnotations()[0].annotationType().getName().equals(RequestBody.class.getName())) {
                                bodyIndex = i;
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            Map<String, Object> inputParamMap = new HashMap<>();
            Enumeration em = request.getParameterNames();
            while (em.hasMoreElements()) {
                String name = (String) em.nextElement();
                inputParamMap.put(name, request.getParameter(name));
            }

            //获取request body
            if (bodyIndex != null) {
                String bodyJson = arguments[bodyIndex] instanceof String ? String.valueOf(arguments[bodyIndex]) : JSON.toJSONString(arguments[bodyIndex]);
                Map bodyMap = JSON.parseObject(bodyJson, Map.class);
                if(bodyMap != null){
                    inputParamMap.putAll(bodyMap);
                }
            }

//            LOGGER.info("APP_ID:{}|SUB_APP_ID:{}|ACCOUNT_NO:{}|OPERATION_NAME:{}|IP:{}|PARAMS:{}|DATE:{}", appId, subAppId, accountNo, operationName.getDescription(),
//                    request.getRemoteAddr(), inputParamMap != null ? inputParamMap.toString() : null, LocalDate.now());

            //insert log
        } catch (Exception e) {
            LOGGER.error("OperationLogAspectj Error,", e);
        }
    }

    /**
     * @Description: 获取request
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
