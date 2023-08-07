package com.example.interceptor;


import com.example.anno.AdminLogin;
import com.example.anno.NoNeedLogin;
import com.example.mapper.WxuserMapper;
import com.example.model.entity.Wxuser;
import com.example.service.WxuserService;
import com.example.utils.AccountHolder;
import com.example.utils.RedisToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    RedisToken redisToken;
    @Resource
    WxuserService wxuserService;
    @Resource
    WxuserMapper wxuserMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("LoginInterceptor已拦截");
        //是不是映射到方法上
        boolean isHandlerMethod = handler instanceof HandlerMethod;
        if (!isHandlerMethod) {
            return true;
        }
        //不需要登录的注解
        boolean isNoNeedLogin = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class) != null;
        if (isNoNeedLogin) {
            log.info("当前方法:{}无需登录", ((HandlerMethod) handler).getMethod().getName());
            return true;
        }

        //需要登录验证
        String token = request.getHeader("token");

        if (token == null) {
            return false;
        }

        String openid = redisToken.getOpenid(token);


        Wxuser user = wxuserMapper.getOneByOpenidWxuser(openid);





        boolean isAdminLogin = ((HandlerMethod) handler).getMethodAnnotation(AdminLogin.class) != null;
        if (isAdminLogin) {
            log.info("当前方法:需管理员登录", ((HandlerMethod) handler).getMethod().getName());

            if (user.getRole().equals(1)){

                AccountHolder.saveUser(user);

                return true;
            }else {

                return false;
            }

        }

        AccountHolder.saveUser(user);

        return true;
    }


    //响应结束 threadLocal移除对象
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccountHolder.removeUser();//移除对象
    }

}