package com.qfedu.controller;


import com.qfedu.vo.JsonBean;
import org.apache.ibatis.binding.BindingException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@ControllerAdvice
@ResponseBody
public class CommonException {

    // 异常处理方法
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonBean constraintViolationExceptione(ConstraintViolationException e){
        Iterator<ConstraintViolation<?>>
                iterator = e.getConstraintViolations().iterator();
        String message = null;
        if (iterator.hasNext()) {
            message += iterator.next().getMessage();
            System.out.println(message);
        }
        return new JsonBean(0, message);
    }

    @ExceptionHandler(BindingException.class)
    public JsonBean bindException(BindingException e){

        return new JsonBean(0,"验证失败");
    }

    @ExceptionHandler(Exception.class)
    public JsonBean exception(Exception e){
        System.out.println(e.getMessage());
        e.printStackTrace();
        return new JsonBean(0, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public JsonBean unauthorizedException(UnauthorizedException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
        return new JsonBean(0, e.getMessage());
    }
}
