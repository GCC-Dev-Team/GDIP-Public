package com.example.utils;

import com.example.common.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

    public  String getOpenid(String token) throws Exception {
        /**
         * 获取加密的token直
         */

        String relaxToken=stringRedisTemplate.opsForValue().get(token);

        if(!TokenUtils.verify(relaxToken)|| StringUtils.isBlank(relaxToken)){

            throw new Exception("token过期或者不正确！");
        }

        return TokenUtils.getAccount(relaxToken);
    }
}
