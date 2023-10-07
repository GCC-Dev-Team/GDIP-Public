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
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @author L
* &#064;description  针对表【forum】的数据库操作Service实现
* &#064;createDate   2023-08-06 12:43:34
 */
@Service
@Transactional
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
    implements ForumService{

    @Resource
    ForumMapper forumMapper;
    @Resource
    WxuserMapper wxuserMapper;

    @Resource
    CategoryService categoryService;

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

        UpdateForumBase updateForumBase = new UpdateForumBase();
        BeanUtils.copyProperties(updatePostRequest,updateForumBase);

        Forum forum = updateBase(updateForumBase);

        if(updatePostRequest.getCategoryId()!=null){

            forum.setCategory(updatePostRequest.getCategoryId());

            forumMapper.updateById(forum);
        }

        return Result.success(forum);

    }

    @Override
    public Result updateAnnounce(UpdateAnnounceRequest updateAnnounceRequest) throws Exception {

        UpdateForumBase updateForumBase = new UpdateForumBase();
        BeanUtils.copyProperties(updateAnnounceRequest,updateForumBase);

        return Result.success(updateBase(updateForumBase));
    }

    public Forum updateBase(UpdateForumBase updateForumBase) throws Exception {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("publisher",user.getId())
                .eq("id", updateForumBase.getId());

        Forum forum = getOne(forumQueryWrapper);

        if(forum==null){
            throw new Exception(ResultCode.PARAM_ERROR.getMessage());
        }

        int temple=0;

        if(updateForumBase.getTitle()!=null){

            forum.setTitle(updateForumBase.getTitle());

            temple=temple+1;
        }

        if (updateForumBase.getMdUrl()!=null){

            forum.setMdFileUrl(updateForumBase.getMdUrl());

            temple=temple+1;
        }


        if(updateForumBase.getImageUrl()!=null){

            forum.setSurfaceImage(updateForumBase.getImageUrl());

            temple=temple+1;
        }

        if(updateForumBase.getSurfaceDescription()!=null){

            forum.setSurfaceDescription(updateForumBase.getSurfaceDescription());

            temple=temple+1;
        }

        if(temple>0){

            this.updateById(forum);
        }

        return forum;
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
     */
    @Override
    public Result getAllAnnounce(PageRequest pageRequest) {
        QueryWrapper<Forum> forumQueryWrapper = new QueryWrapper<>();

        forumQueryWrapper.eq("category","admin1")
                .orderByDesc("updated_at");

        Page<Forum> forumPage = forumMapper.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), forumQueryWrapper);

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

        Page<Forum> forumPage = forumMapper.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), forumQueryWrapper);

        PageVO<ForumSmallVO> pageVO = new PageVO<>(forumsToSmallVOs(forumPage.getRecords()), forumPage.getTotal(), forumPage.getSize(), forumPage.getCurrent());

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

    @Override
    public String getMdContextByName(String name) {

        return null;
    }
}




