package com.cxf.ssm_one.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author always_on_the_way
 * @date 2019-06-27
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, HttpServletRequest request){
        //判断请求是否是ajax
        if (isAjax(request)){
            return "redirect:/ajaxError";
        }else {
            return "redirect:/error";
        }
    }


    private boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");

        return header != null && header.contains("XMLHttpRequest");

    }


}
