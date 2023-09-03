package com.example.controller;


import com.example.common.Result;
import com.example.service.LikeDislikeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 点赞与踩(根评论才能点赞)
 */
@RestController
@RequestMapping("/like")
public class LikeOrDIsLikeController {
    @Resource
    LikeDislikeService likeDislikeService;

    /**
     * 可以是子评论id，也可以是根评论id,点赞
     * @param commentId
     * @return
     */
    @PostMapping
    public Result creatLike(@NotNull String commentId){

        return likeDislikeService.creatLike(commentId);
    }

    /**
     * 判断是否已经喜欢了
     * @param commentId
     * @return
     */
    @GetMapping
    public Boolean judgeIsLike(@NotNull String commentId){

        return likeDislikeService.judgeIsLike(commentId);
    }


}
