package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Followers;
import com.example.service.FollowersService;
import com.example.mapper.FollowersMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【followers】的数据库操作Service实现
* @createDate 2023-08-09 00:55:34
*/
@Service
public class FollowersServiceImpl extends ServiceImpl<FollowersMapper, Followers>
    implements FollowersService{

}




