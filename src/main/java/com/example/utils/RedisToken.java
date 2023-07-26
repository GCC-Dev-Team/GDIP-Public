package com.example.utils;

import com.example.common.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RedisToken {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    public  String getOpenid(String token){
        /**
         * 获取加密的token
         */

        String relaxToken=stringRedisTemplate.opsForValue().get(token);

        if(!TokenUtils.verify(relaxToken)){
            return "token解密出现错误";
        }

        return TokenUtils.getAccount(relaxToken);
    }
}
