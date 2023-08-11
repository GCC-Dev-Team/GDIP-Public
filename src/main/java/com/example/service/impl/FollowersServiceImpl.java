package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.PageRequest;
import com.example.model.entity.Followers;
import com.example.model.entity.Wxuser;
import com.example.model.vo.FollowerUserVO;
import com.example.model.vo.PageVO;
import com.example.service.FollowersService;
import com.example.mapper.FollowersMapper;
import com.example.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
* @author L
* @description 针对表【followers】的数据库操作Service实现
* @createDate 2023-08-09 00:55:34
*/
@Service
public class FollowersServiceImpl extends ServiceImpl<FollowersMapper, Followers>
    implements FollowersService{
    @Resource
    FollowersMapper followersMapper;
    @Override
    public Result follower(String beFollowerId) {
        Wxuser user = AccountHolder.getUser();

        Followers one = getFollowersByUserAndFollowedId(user.getId(), beFollowerId);

        if(one==null){

            Followers followers = new Followers();

            followers.setFollowId("Follower:"+UUID.randomUUID());
            followers.setFollowedUserId(user.getId());
            followers.setFollowedUserId(beFollowerId);

            save(followers);

            return Result.success("关注成功!");
        }

        //说明已经关注了
        return Result.failure(ResultCode.PARAM_IS_INVALID,"已经关注了，请勿重复关注");

    }

    @Override
    public Result updateMyFollower(String beFollowerId) {
        Wxuser user = AccountHolder.getUser();

        Followers one = getFollowersByUserAndFollowedId(user.getId(), beFollowerId);

        if(one==null){

            return Result.failure(ResultCode.PARAM_IS_INVALID,"没有关注该用户");
        }

        followersMapper.deleteById(one);

        return Result.success();
    }

    public Followers getFollowersByUserAndFollowedId(String userId, String followedUserId) {
        QueryWrapper<Followers> followersQueryWrapper = new QueryWrapper<>();
        followersQueryWrapper.eq("user_id", userId)
                .eq("followed_user_id", followedUserId);

        return getOne(followersQueryWrapper);
    }

    @Override
    public Result getMyALlFollower(PageRequest pageRequest) {
        Wxuser user = AccountHolder.getUser();

        IPage<FollowerUserVO> myAllFollowers = followersMapper.getMyAllFollowers(user.getId(), new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()));

        PageVO<FollowerUserVO> followerUserVOPageVO = new PageVO<>(myAllFollowers.getRecords(),myAllFollowers.getTotal(),myAllFollowers.getSize(),myAllFollowers.getCurrent());

        return Result.success(followerUserVOPageVO);
    }
}




