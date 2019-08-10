package com.cxf.ssm_one.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author always_on_the_way
 * @date 2019-06-27
 */
@Controller
public class ErrorController {

    @ResponseBody
    @RequestMapping(value = "/ajaxError")
    public String ajaxError(){
        return "ajax error";
    }

    @RequestMapping(value = "/error")
    public String error(){
        return "error";
    }

    @RequestMapping("/unauth")
    public String unauth(){
        return "unauth";
    }
}
