package com.example.service;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.model.entity.Followers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【followers】的数据库操作Service
* @createDate 2023-08-09 00:55:34
*/
public interface FollowersService extends IService<Followers> {

    Result follower(@NotNull String beFollowerId);

    Result updateMyFollower(@NotNull String beFollowerId);

    Result getMyALlFollower(@NotNull@RequestBody PageRequest pageRequest);

}
