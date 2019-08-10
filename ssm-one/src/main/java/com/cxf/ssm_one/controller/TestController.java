package com.cxf.ssm_one.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author always_on_the_way
 * @date 2019-06-28
 */
@Controller
public class TestController {

//    可以两个一起用，一般只用permission的就够了
//    @RequiresRoles("admin")
    @RequiresPermissions("test")
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "请求成功";
    }

}
