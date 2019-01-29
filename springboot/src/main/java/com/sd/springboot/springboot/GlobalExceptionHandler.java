package com.sd.springboot.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Component
public class GlobalExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    String handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }


    /**
     * 处理实体字段校验不通过异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append( "\n" + error.getDefaultMessage());
        }
        logger.error(builder.toString());
        return builder.toString();
    }

}