package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.*;
import com.example.model.entity.Forum;
import com.example.model.entity.Wxuser;
import com.example.model.vo.ForumDescribeVO;
import com.example.model.vo.ForumSmallVO;
import com.example.model.vo.PageVO;
import com.example.service.CategoryService;
import com.example.service.ForumService;
import com.example.mapper.ForumMapper;
import com.example.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @author L
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2023-08-06 12:43:34
*/
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
    implements ForumService{

    @Resource
    ForumMapper forumMapper;
    @Resource
    WxuserMapper wxuserMapper;

    @Resource
    CategoryService categoryService;
    @Override
    public String uploadMdPhoto(@NotNull MultipartFile file) {

        String nameFile="mdPhoto:"+ UUID.randomUUID();

        UploadPhotoUtil.uploadFile(file,nameFile);

        return ShowPhotoUtil.getPhotoByName(nameFile);
    }

    @Override
    public String uploadMd(MultipartFile file) throws Exception {
        Wxuser user = AccountHolder.getUser();

        String name="md:"+user.getId()+UUID.randomUUID();

        QiniuUtil.uploadImage(file.getBytes(), name+".txt");

        return QiniuUtil.getImageUrl(name+".txt");
    }

    @Override
    public Result addPost(AddPostRequest addPostRequest) {
        Wxuser user = AccountHolder.getUser();

        Forum forum = new Forum();
        forum.setId("Post:"+UUID.randomUUID());
        forum.setTitle(addPostRequest.getTitle());
        forum.setCategory(addPostRequest.getCategoryId());
        forum.setSurfaceDescription(addPostRequest.getSurfaceDescription());
        return getResult(user, forum, addPostRequest.getMdUrl(), addPostRequest.getImageUrl());
    }

    @NotNull
    private Result getResult(Wxuser user, Forum forum, String mdUrl, String imageUrl) {
        forum.setPublisher(user.getId());
        forum.setViews(0);
        forum.setMdFileUrl(mdUrl);
        forum.setSurfaceImage(imageUrl);

        ForumDescribeVO forumDescribeVO = new ForumDescribeVO();
        BeanUtils.copyProperties(forum,forumDescribeVO);

        save(forum);

        forumDescribeVO.setAvatar(user.getAvatar());

        forumDescribeVO.setNickName(user.getUserName());


        return Result.success(forumDescribeVO);
    }

    @Override
    public Result updatePost(UpdatePostRequest updatePostRequest) throws Exception {

        Wxuser user;
        user = AccountHolder.getUser();

        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("publisher",user.getId())
                .eq("id", updatePostRequest.getId());

        Forum one = getOne(forumQueryWrapper);
        if(one==null){
            throw new Exception(ResultCode.PARAM_ERROR.getMessage());
        }

        int temple=0;

        if(updatePostRequest.getTitle()!=null){

            one.setTitle(updatePostRequest.getTitle());

            temple=temple+1;
        }

        if (updatePostRequest.getMdUrl()!=null){

            one.setMdFileUrl(updatePostRequest.getMdUrl());

            temple=temple+1;
        }


        if(updatePostRequest.getImageUrl()!=null){

            one.setSurfaceImage(updatePostRequest.getImageUrl());

            temple=temple+1;
        }

        if(updatePostRequest.getCategoryId()!=null){

            one.setCategory(updatePostRequest.getCategoryId());

            temple=temple+1;
        }
        if(updatePostRequest.getSurfaceDescription()!=null){

            one.setSurfaceDescription(updatePostRequest.getSurfaceDescription());

            temple=temple+1;
        }

        if(temple>0){

            this.updateById(one);
        }

        return Result.success(getById(updatePostRequest.getId()));

    }

    @Override
    public Result updateAnnounce(UpdateAnnounceRequest updateAnnounceRequest) throws Exception {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("publisher",user.getId())
                .eq("id", updateAnnounceRequest.getId());

        Forum one = getOne(forumQueryWrapper);
        if(one==null){
            throw new Exception(ResultCode.PARAM_ERROR.getMessage());
        }

        int temple=0;

        if(updateAnnounceRequest.getTitle()!=null){

            one.setTitle(updateAnnounceRequest.getTitle());

            temple=temple+1;
        }

        if (updateAnnounceRequest.getMdUrl()!=null){

            one.setMdFileUrl(updateAnnounceRequest.getMdUrl());

            temple=temple+1;
        }


        if(updateAnnounceRequest.getImageUrl()!=null){

            one.setSurfaceImage(updateAnnounceRequest.getImageUrl());

            temple=temple+1;
        }

        if(updateAnnounceRequest.getSurfaceDescription()!=null){

            one.setSurfaceDescription(updateAnnounceRequest.getSurfaceDescription());

            temple=temple+1;
        }

        if(temple>0){

            this.updateById(one);
        }

        return Result.success(getById(updateAnnounceRequest.getId()));
    }

    @Override
    public Result getMyAnnounce() {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("publisher",user.getId()).orderByDesc("updated_at");

        List<Forum> forums = forumMapper.selectList(forumQueryWrapper);

        List<ForumSmallVO> forumSmallVOS = forumsToSmallVOs(forums);


        return Result.success(forumSmallVOS);

    }

    private List<ForumSmallVO> forumsToSmallVOs(List<Forum> forums) {
        List<ForumSmallVO> forumSmallVOS = new ArrayList<>();

        for (Forum forum : forums) {

            ForumSmallVO forumSmallVO = new ForumSmallVO();

            BeanUtils.copyProperties(forum, forumSmallVO);

            String categoryId = forum.getCategory();

            forumSmallVO.setCategoryName(categoryService.getNameOfCategory(categoryId));

            Wxuser wxuser = wxuserMapper.selectById(forum.getPublisher());


            forumSmallVO.setAvatar(wxuser.getAvatar());

            forumSmallVO.setNiceName(wxuser.getUserName());

            forumSmallVO.setUpdateDate(forum.getUpdatedAt());

            forumSmallVOS.add(forumSmallVO);

        }
        return forumSmallVOS;
    }

    /**
     * 获取所有的公告
     * @return
     */
    @Override
    public Result getAllAnnounce(PageRequest pageRequest) {
        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("category","admin1")
                .orderByDesc("updated_at");

        Page<Forum> forumPage = forumMapper.selectPage(new Page<Forum>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), forumQueryWrapper);

        PageVO<ForumSmallVO> pageVO = new PageVO<>(forumsToSmallVOs(forumPage.getRecords()), forumPage.getTotal(), forumPage.getSize(), forumPage.getCurrent());

        return Result.success(pageVO);
    }

    @Override
    public Result getDescribePost(String id) {

        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("id",id);

        Forum one = getOne(forumQueryWrapper);

        ForumDescribeVO forumDescribeVO = new ForumDescribeVO();
        BeanUtils.copyProperties(one,forumDescribeVO);

        Wxuser oneByOpenidWxuser = wxuserMapper.selectById(one.getPublisher());

        forumDescribeVO.setNickName(oneByOpenidWxuser.getUserName());

        forumDescribeVO.setAvatar(oneByOpenidWxuser.getAvatar());

        return Result.success(forumDescribeVO);
    }

    @Override
    public Result getAllPost(PageRequest pageRequest) {
        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();


        forumQueryWrapper.ne("category","admin1")
                .orderByDesc("updated_at");

        Page<Forum> forumPage = forumMapper.selectPage(new Page<Forum>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), forumQueryWrapper);

        PageVO<ForumSmallVO> pageVO = new PageVO<>(forumsToSmallVOs(forumPage.getRecords()), forumPage.getTotal(), forumPage.getSize(), forumPage.getSize());

        return Result.success(pageVO);
    }

    @Override
    public Result addAnnouncement(AddAnnouncementRequest addAnnouncementRequest) {
        Wxuser user = AccountHolder.getUser();

        Forum forum = new Forum();
        forum.setId("Announcement:"+UUID.randomUUID());
        forum.setTitle(addAnnouncementRequest.getTitle());
        forum.setCategory("admin1");
        forum.setSurfaceDescription(addAnnouncementRequest.getSurfaceDescription());
        return getResult(user, forum, addAnnouncementRequest.getMdUrl(), addAnnouncementRequest.getImageUrl());
    }

    @Override
    public Result deletePhotos(String[] fileNames) {
        return Result.success(DeletePhotoUtil.deletePhotos(fileNames));
    }


    @Override
    public Result deletePost(String postId) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("publisher",user.getId()).eq("id",postId);

        Forum one = getOne(forumQueryWrapper);

        if(one==null){
            return Result.failure(ResultCode.SYSTEM_ERROR,"该帖子并非本账号发布");
        }

        forumMapper.deleteById(postId);

        return Result.success("删除成功!");
    }
}




