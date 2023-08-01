package com.example.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.config.WxMaProperties;
import com.example.model.dto.WxLoginRequest;
import com.example.model.entity.Wxuser;
import com.example.model.vo.WxLoginVO;
import com.example.service.WxLoginService;
import com.example.service.WxuserService;
import com.example.utils.TokenUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class WxLoginServiceImpl implements WxLoginService {
    @Resource
    WxMaService wxMaService;
    @Resource
    WxuserService wxuserService;
    private WxMaProperties wxMaProperties;

    @Resource
    StringRedisTemplate stringRedisTemplate;



    @Override
    public Result wxLogin(WxLoginRequest wxLoginRequest) {



        String code = wxLoginRequest.getCode();

        WxMaProperties.Config config = wxMaProperties.getConfigs().get(0);

        String appid = config.getAppid();
        if (StringUtils.isBlank(code)) {

            return Result.failure(ResultCode.PARAM_IS_BLANK,"code为null！");

        }

        if (!wxMaService.switchover(appid)) {
            log.error("未找到对应appid={}的配置，请核实！", appid);
            return Result.failure(ResultCode.PARAM_IS_BLANK,"appid出错!");
        }

        try {
            WxMaJscode2SessionResult session  = wxMaService.getUserService().getSessionInfo(code);

            String sessionKey = session.getSessionKey();
            String openid = session.getOpenid();

            log.debug(sessionKey);
            log.debug(openid);

            // 查询openid是否存在数据库并获取用户信息
            Wxuser user = checkLoginByOpenid(openid);

            if (Objects.isNull(user)) {
                // 不存在，注册新用户，并将openid存储进数据库
                user = registerNewUser(openid);
            }

            String token= TokenUtils.getToken(openid);


            WxLoginVO wxLoginVo=new WxLoginVO();

            String redisToken=UUID.randomUUID().toString();

            wxLoginVo.setAvatar(user.getAvatar());
            wxLoginVo.setState(user.getState());
            wxLoginVo.setRole(user.getRole());
            wxLoginVo.setStudentNumber(user.getStudentNumber());
            wxLoginVo.setUserName(user.getUserName());
            wxLoginVo.setPhoneNumber(user.getPhoneNumber());
            wxLoginVo.setToken(redisToken);


            if (token != null) {
                stringRedisTemplate.opsForValue().set(redisToken,token);
            }


            return Result.success(wxLoginVo);

        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }finally {

            WxMaConfigHolder.remove();//清理ThreadLocal
        }

    }

    private Wxuser checkLoginByOpenid(String openid) {
        // 查询数据库，根据openid判断用户是否已登录过
        LambdaQueryWrapper<Wxuser> queryWrapper = new LambdaQueryWrapper<Wxuser>().eq(Wxuser::getOpenid, openid);
        return wxuserService.getOne(queryWrapper);
    }

    private Wxuser registerNewUser(String openid) {
        // 注册新用户，将openid存储进数据库
        Wxuser user = new Wxuser();
        user.setId(UUID.randomUUID().toString());
        user.setPhoneNumber("");
        user.setPassword("");
        user.setOpenid(openid);
        // 默认用户角色
        user.setRole(0);
        // 随机用户名
        user.setUserName("qing_" + UUID.randomUUID().toString().substring(0,9));
        // 正常状态
        user.setState(0);

        wxuserService.save(user);

        return user;
    }
}
