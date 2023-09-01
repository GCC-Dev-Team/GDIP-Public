package com.example.controller;

import com.example.anno.AdminLogin;
import com.example.anno.NoNeedLogin;
import com.example.common.Result;
import com.example.model.dto.*;

import com.example.service.ForumService;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 帖子
 */
@RestController
@RequestMapping("/forum")
public class ForumController {

    @Resource
    ForumService forumService;


    /**
     * 发布公告
     */
    @PostMapping("/addAnnouncement")
    @AdminLogin
    public Result addAnnouncement(@NotNull @RequestBody AddAnnouncementRequest addAnnouncementRequest){

        return forumService.addAnnouncement(addAnnouncementRequest);
    }
    /**
     * 发布学校的帖子/文章（非公告）
     * @param addPostRequest
     * @return
     */
    @PostMapping("/addPost")
    public Result addPost(@NotNull @RequestBody AddPostRequest addPostRequest) {

        return forumService.addPost(addPostRequest);
    }

    /**
     * 修改公告
     */
    @PutMapping("/updateAnnounce")
    @AdminLogin
    public Result updateAnnounce(@NotNull @RequestBody UpdateAnnounceRequest updateAnnounceRequest) throws Exception {

        return forumService.updateAnnounce(updateAnnounceRequest);

    }

    /**
     * 修改帖子
     */
    @PutMapping("/updatePost")
    public Result updatePost(@NotNull @RequestBody UpdatePostRequest updatePostRequest) throws Exception {

        return forumService.updatePost(updatePostRequest);
    }
    /**
     * 查看帖子（详细的）
     * @param id 文章/帖子的id
     * @return
     */
    @GetMapping("/describe")
    @NoNeedLogin
    public Result getDescribePost(@NotNull String id){

        return forumService.getDescribePost(id);
    }

    /**
     * 简单的帖子展示(实现分页操作)(所有的)
     */
    @PostMapping("/allPost")
    @NoNeedLogin
    public Result getAllPost(@NotNull @RequestBody PageRequest pageRequest){

        return forumService.getAllPost(pageRequest);
    }

    /**
     * 简单的公告展示(实现分页操作)
     */
    @PostMapping("/allAnnounce")
    @NoNeedLogin
    public Result getAllAnnounce(@NotNull @RequestBody PageRequest pageRequest){

        return  forumService.getAllAnnounce(pageRequest);
    }


    /**
     * 查看我发布的公告或者文章
     */
    @GetMapping("/myAnnounce")
    public Result getMyAnnounce(){
        return forumService.getMyAnnounce();
    }

    /**
     * 删除帖子
     */
    @DeleteMapping("/myAnnounce")
    public Result deletePost(String postId){

        return forumService.deletePost(postId);
    }

}
