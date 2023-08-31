package com.example.utils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    //180 * 60 * 60 * 1000 //180个小时
    private static final long EXPIRE_DATE =  180 * 60 * 60 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "xiaoli";

    /**
     * 生成jwt
     * @param openId
     * @param
     * @return
     */

    public static String generateJWT(String openId, String redisToken) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_DATE);

        return Jwts.builder()
                .claim("openId", openId)
                .claim("redisToken",redisToken)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .compact();
    }

    public static String getOpenid(String jwt){

        Claims claims = Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get("openId", String.class);
    }
    public static String getRedisToken(String jwt){

        Claims claims = Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get("redisToken", String.class);
    }



    //失效了返回ture，没有返回flase
    public static boolean isTokenExpired(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(jwt)
                    .getBody();

            long expirationTime = claims.getExpiration().getTime();
            long currentTime = System.currentTimeMillis();

            return expirationTime < currentTime;
        } catch (ExpiredJwtException e) {
            // JWT has expired
            return true;
        } catch (Exception e) {
            // Other exceptions, token could be tampered or invalid
            return false; // Return false for other exceptions
        }
    }

//    //老token
//
//    public static String getToken(String account) {
//        String token = "";
//        try {
//            //过期时间
//            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
//            //秘钥及加密算法
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            //携带username，password信息，生成签名
//            token = JWT.create()
//                    .withClaim("account", account)
//                    .withExpiresAt(date)
//                    .sign(algorithm);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return token;
//    }
//
//    /**
//     * @desc 验证token，通过返回true
//     * @params [token]需要校验的串
//     **/
//    public static Boolean verify(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * 从token中获取信息,无需解密
//     *
//     * @param token
//     * @return
//     */
//    public static String getAccount(String token) {
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            if (System.currentTimeMillis() - jwt.getExpiresAt().getTime() > 0) {
//                return null;
//            }
//            return jwt.getClaim("account").asString();
//        } catch (Exception e) {
//            return null;
//        }
//    }

}

