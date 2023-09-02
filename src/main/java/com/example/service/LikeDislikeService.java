package com.example.service;

import com.example.common.Result;
import com.example.model.entity.LikeDislike;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【like_dislike】的数据库操作Service
* @createDate 2023-09-02 10:16:14
*/
public interface LikeDislikeService extends IService<LikeDislike> {


    Result creatLike(@NotNull String commentId);

}
