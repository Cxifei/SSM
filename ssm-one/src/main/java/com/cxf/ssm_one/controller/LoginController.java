package com.cxf.ssm_one.controller;

import com.cxf.ssm_one.service.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录控制层
 *
 * @author always_on_the_way
 * @date 2019-06-26
 */
@Controller
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request){

        String userInfo = request.getParameter("username");
        if (userInfo == null){
            return "login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userInfo",userInfo);
        return "redirect:/home";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("msg","登录成功");
        return "home";
    }

    @RequestMapping(value = "/loginShiro",method = RequestMethod.GET)
    public String login(){
        //判断用户是否登录而进行的拦截，如果已经登录过的，就算想到登录页面也无法再跳转登录页面，直接到主页
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "redirect:/info";
        }
        return "login";
    }


    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginShiro",method = RequestMethod.POST)
    public String loginShiro(String username,String password){
        //判断用户是否登录
        Subject subject = SecurityUtils.getSubject();
        //判断是否已经登录了
        //如果没有登录就走if登录一下再跳转info
        if (!subject.isAuthenticated()){
            //没有登录
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            //TODO 登录成功后的操作
            System.out.println("登录成功");
        }
        //已经登录过了就直接跳转到info
        return "redirect:/info";
    }


    /**
     * 注册功能
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(String username,String password){
        loginService.register(username,password);
        return "";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        //退出登录，不用自己写，shiro自动帮你清空session等记录
        SecurityUtils.getSubject().logout();
        return "login";
    }

}
