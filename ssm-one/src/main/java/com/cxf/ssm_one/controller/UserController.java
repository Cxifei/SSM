package com.cxf.ssm_one.controller;

import com.cxf.ssm_one.pojo.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author always_on_the_way
 * @date 2019-06-26
 */
@Controller
public class UserController {

    @RequiresPermissions("info")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info(Model model){
        User user = new User();
        user.setId(1L);
        user.setName("渣哥");
        user.setAddress("成都");
        user.setTelephone("138");
        user.setAge(18);
        user.setSex("男");

//      将一个类的属性复制到另一个类
//        方法一（比较笨）
//      User1 user1 = new User1();
//      user1.setName(user.getName());

//        方法二（使用springmvc自带的工具类，推荐使用）
//      BeanUtils.copyProperties(user,user1);

        model.addAttribute("user",user);
        return "info";
    }

}
