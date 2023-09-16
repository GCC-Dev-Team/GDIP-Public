package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.mapper.LikeDislikeMapper;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.AddCommentRequest;
import com.example.model.dto.PageRequest;
import com.example.model.entity.Comment;
import com.example.model.entity.LikeDislike;
import com.example.model.entity.Wxuser;
import com.example.model.vo.CommentVO;
import com.example.model.vo.PageVO;
import com.example.service.CommentService;
import com.example.mapper.CommentMapper;
import com.example.utils.AccountHolder;
import com.example.utils.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
* @author L
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-09-01 11:52:18
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Resource
    CommentMapper commentMapper;
    @Resource
    WxuserMapper wxuserMapper;

    @Resource
    LikeDislikeMapper likeDislikeMapper;
    @Override
    public Result addComment(AddCommentRequest addCommentRequest) {
        Wxuser user = AccountHolder.getUser();

        Comment comment = new Comment();

        BeanUtils.copyProperties(addCommentRequest,comment);
        comment.setCommenterId(user.getId());
        comment.setCommentId("comment:"+ RandomUtil.generateRandomString(20));//20位是跟评论
        comment.setLikes(0);
        comment.setDislikes(0);
        comment.setSubCommentCount(0);
        save(comment);
        return Result.success();
    }

    /**
     * 获取根评论
     * @param forumId 帖子的id
     * @param pageRequest 分页情况
     * @return
     */

    @Override
    public Result getComments(String forumId, PageRequest pageRequest) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("post_id", forumId).orderByDesc("updated_at").orderByDesc("likes");
        Page<Comment> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());

        Page<Comment> commentPage = commentMapper.selectPage(page, commentQueryWrapper);

        List<Comment> records = commentPage.getRecords();

        List<CommentVO> commentVOS =records.stream().map(comment -> new CommentVO(
                comment.getCommentId(),
                comment.getPostId(),
                comment.getCommenterId(),
                comment.getCommentContent(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getCreatedAt(),
                 wxuserMapper.selectById(comment.getCommenterId()).getAvatar(),
                 wxuserMapper.selectById(comment.getCommenterId()).getUserName(),
                comment.getSubCommentCount()
        )).toList();

        return Result.success(new PageVO<>(commentVOS,page.getTotal(),page.getSize(),page.getCurrent()));
    }


    @Override
    public Result deleteComment(String commentId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("comment_id",commentId).eq("commenterId",user.getId());

        Comment comment = getOne(commentQueryWrapper);

        if (comment==null){

            throw new RuntimeException("该评论删除失败!");
        }

        commentMapper.deleteById(commentId);

        QueryWrapper<LikeDislike> queryWrapper = new QueryWrapper<LikeDislike>().eq("comment_id", commentId);

        likeDislikeMapper.delete(queryWrapper);

        return Result.success();
    }
}




