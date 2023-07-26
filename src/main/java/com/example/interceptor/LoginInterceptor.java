package com.example.interceptor;


import com.example.utils.AccountHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        log.info("LoginInterceptor已拦截");
//        //是不是映射到方法上
//        boolean isHandlerMethod = handler instanceof HandlerMethod;
//        if (!isHandlerMethod) {
//            return true;
//        }
//        //不需要登录的注解
//        boolean isNoNeedLogin = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class) != null;
//        if (isNoNeedLogin) {
//            log.info("当前方法:{}无需登录", ((HandlerMethod) handler).getMethod().getName());
//            return true;
//        }
//
////        ((HandlerMethod) handler).getMethodAnnotation(BasicErrorController.class)!=null;
//        //需要登录验证
//        String token = request.getHeader(LOGIN_HEADER);
//
//        if (StringUtils.hasText(token)) {
//            // 此处做token及其身份验证
//            return verifyToken(token);
//
//        }
//
//        throw new UserLoginException(ResultCode.USER_TOKEN_IS_BLANK);
        return true;
    }

//    /**
//     * 使用token进行身份验证
//     *
//     * @param token
//     * @return
//     */
//    public Boolean verifyToken(String token) {
////        if (!"token".equals(token)) {
////            throw new UserLoginException(ResultCode.USER_TOKEN_IS_INVALID);
////        }
////        log.info("成功");
//        Claims claims = null;
//        try {
//            claims = TokenUtil.parseJwt(token);
//        } catch (Exception e) {
//            log.info("token有误,当前claims为：{}", claims);
//            throw new UserLoginException(ResultCode.USER_TOKEN_IS_INVALID);
//        }
//        //验证逻辑
//        if (null != claims) {
//            String id = claims.getId();
//
//            User user = userService.getById(id);
//            //查询redis中是否存在对应的token
//            log.info("查询redis中是否存在对应的token");
//            String redisToken = cacheClient.queryWithLogicalExpire(LOGIN_USER_KEY + user.getId(), String.class);
//            //查询token是否存在且相等，则将user保存到本地线程
//            if (StringUtils.hasLength(redisToken) && redisToken.equals(token) && user != null) {
//                // 将用户放在threadLocal中
//                log.info("用户已放入threadLocal中");
//                AccountHolder.saveUser(user);
//                return true;
//            }else {
//                //用户token与redis中不匹配
//                throw new UserLoginException(ResultCode.USER_TOKEN_IS_INVALID);
//            }
//        }
//        return false;
//    }


    //响应结束 threadLocal移除对象
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccountHolder.removeUser();//移除对象
    }

}