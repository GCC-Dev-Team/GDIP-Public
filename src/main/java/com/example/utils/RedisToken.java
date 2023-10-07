package com.example.utils;

import com.example.common.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Slf4j
public class RedisToken {

    /**
     * 设置过期时间
     */
    //180 * 60 * 60 * 1000 //180个小时
    private static final long EXPIRE_DATE =  180 * 60 * 60 * 1000;
    @Resource
    StringRedisTemplate stringRedisTemplate;

//    public  String getOpenid(String token) throws Exception {
//        /**
//         * 获取加密的token直
//         */
//
//        String relaxToken=stringRedisTemplate.opsForValue().get(token);
//
//        if(!TokenUtils.verify(relaxToken)|| StringUtils.isBlank(relaxToken)){
//
//            throw new Exception("token过期或者不正确！");
//        }
//
//        return TokenUtils.getAccount(relaxToken);
//    }


    public String getOpenId(String jwt){

        boolean tokenExpired = TokenUtils.isTokenExpired(jwt);

        if(tokenExpired){

            try {

                log.error("JWT:token过期或者不正确！");
                return null;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String openid = TokenUtils.getOpenid(jwt);
        String redisToken = TokenUtils.getRedisToken(jwt);

        Boolean judgeRedisToken = judgeRedisToken(openid, redisToken);

        if (judgeRedisToken==Boolean.TRUE){

            return openid;
        }

        log.error("redis认证失败!");
        return null;
    }

    public Boolean judgeRedisToken(@NotNull String openId,@NotNull String redisToken){

        /*准确的redisToken*/
        String redisTokenAccurate = stringRedisTemplate.opsForValue().get(openId);

        //如果相同返回ture表示判断正确，也就是信息正确
        if (redisTokenAccurate != null) {
            return redisTokenAccurate.equals(redisToken);
        }

        log.error("redis数据判断错误!");

        return false;
    }

    /**
     * 返回token（Jwt）
     * @param openId
     * @return
     */
    public String saveRedis(@NotNull String openId){

        String redisToken = RandomUtil.generateRandomString(16);

        stringRedisTemplate.opsForValue().set(openId,redisToken);

        return TokenUtils.generateJWT(openId, redisToken);
    }
}
