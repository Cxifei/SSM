package com.cxf.ssm_one.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 * @author always_on_the_way
 * @date 2019-06-26
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    /**
     * 判断用户是否登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");

        if (userInfo == null){
            response.sendRedirect("login.jsp");
            return false;
        }

        return true;
    }



//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("方法执行完后");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("视图渲染完成");
//    }

}
