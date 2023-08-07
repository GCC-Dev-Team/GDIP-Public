package com.example.controller;

import com.example.anno.AdminLogin;
import com.example.anno.NoNeedLogin;
import com.example.common.Result;
import com.example.model.dto.*;

import com.example.service.CategoryService;
import com.example.service.ForumService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/forum")
public class ForumController {

    @Resource
    CategoryService categoryService;

    @Resource
    ForumService forumService;

    /**
     * 增加类别
     *
     * @param categoryName 类别名称
     * @return
     */
    @AdminLogin
    @PostMapping("/addCategory")
    public Result addCategory(@NotNull String categoryName) {

        return categoryService.addCategory(categoryName);
    }

    /**
     * 所有类别
     */
    @NoNeedLogin
    @GetMapping("/allCategory")
    public Result showAllCategory() {
        return categoryService.showAllCategory();
    }

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
    @GetMapping("/allPost")
    @NoNeedLogin
    public Result getAllPost(@NotNull @RequestBody PageRequest pageRequest){

        return forumService.getAllPost(pageRequest);
    }

    /**
     * 简单的公告展示(实现分页操作)
     */
    @GetMapping("/allAnnounce")
    @NoNeedLogin
    public Result getAllAnnounce(@NotNull @RequestBody PageRequest pageRequest){

        return  forumService.getAllAnnounce(pageRequest);
    }

    /**
     * 上传图片
     */
    @PostMapping("/uploadPhoto")
    public String uploadMdPhoto(@NotNull MultipartFile file) {

        return forumService.uploadMdPhoto(file);
    }

    /**
     * 上传md文件
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadMd")
    public String uploadMd(@NotNull MultipartFile file) throws Exception {

        return forumService.uploadMd(file);
    }

    /**
     * 查看我发布的公告或者文章
     */
    @GetMapping("/myAnnounce")
    public Result getMyAnnounce(){
        return forumService.getMyAnnounce();
    }

}
