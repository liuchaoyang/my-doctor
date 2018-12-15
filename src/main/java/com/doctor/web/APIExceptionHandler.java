package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.exception.APIBaseException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class APIExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(APIExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public void handle(Exception ex, HttpServletResponse response) throws IOException {
        logger.error("exception:", ex);
        ResultJson resultJson = new ResultJson();
        if (ex instanceof APIBaseException) {
            resultJson.setCode(((APIBaseException) ex).getCode());
            resultJson.setMessage(ex.getMessage());
        } else {
            resultJson.setCode(500);
            resultJson.setMessage("位置异常");
        }
        output(response, resultJson);

    }

    private void output(HttpServletResponse response, ResultJson result) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(result);
        response.getWriter().write(json);
        response.flushBuffer();
    }
}
