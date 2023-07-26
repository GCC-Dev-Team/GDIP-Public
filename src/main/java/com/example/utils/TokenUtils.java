package com.example.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 获取token
 */
@Slf4j
public class TokenUtils {
    /**
     * 设置过期时间
     */
    private static final long EXPIRE_DATE = 180 * 60 * 60 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "xiaoli";

    public static String getToken(String account) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withClaim("account", account)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * @desc 验证token，通过返回true
     * @params [token]需要校验的串
     **/
    public static Boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从token中获取信息,无需解密
     *
     * @param token
     * @return
     */
    public static String getAccount(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            if (System.currentTimeMillis() - jwt.getExpiresAt().getTime() > 0) {
                return null;
            }
            return jwt.getClaim("account").asString();
        } catch (Exception e) {
            return null;
        }
    }

}

