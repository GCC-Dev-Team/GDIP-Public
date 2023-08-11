package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.entity.Followers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Wxuser;
import com.example.model.vo.FollowerUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author L
* @description 针对表【followers】的数据库操作Mapper
* @createDate 2023-08-09 00:55:34
* @Entity com.example.model.entity.Followers
*/
public interface FollowersMapper extends BaseMapper<Followers> {


    //SELECT wxuser.id,wxuser.user_name,wxuser.avatar FROM followers LEFT JOIN wxuser ON followed_user_id=id WHERE user_id=

    @Select("SELECT wxuser.id,wxuser.user_name,wxuser.avatar FROM followers LEFT JOIN wxuser ON followed_user_id=id WHERE user_id=#{userId}")
    IPage<FollowerUserVO> getMyAllFollowers(@Param("userId")String userId, Page<FollowerUserVO> page);
}




