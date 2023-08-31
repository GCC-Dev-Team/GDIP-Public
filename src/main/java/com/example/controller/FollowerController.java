package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.service.FollowersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/follower")
public class FollowerController {
    @Resource
    FollowersService followersService;
    //我的关注

    /**
     * 关注
     * @param beFollowerId
     * @return
     */

    @PostMapping("/Follower")
    public Result follower(@NotNull String beFollowerId){

        return followersService.follower(beFollowerId);
    }

    /**
     * 取消关注
     * @param beFollowerId
     * @return
     */
    @PutMapping("/Follower")
    public Result updateMyFollower(@NotNull String beFollowerId){

        return followersService.updateMyFollower(beFollowerId);
    }

    //查看我的关注

    /**
     * 查看我的关注用户
     * @param pageRequest
     * @return
     */
    @PostMapping("/myAllFollowers")
    public Result getMyALlFollower(@NotNull@RequestBody PageRequest pageRequest){
        return  followersService.getMyALlFollower(pageRequest);
    }

}
