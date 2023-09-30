package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.mapper.CommentMapper;
import com.example.mapper.LikeDislikeMapper;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.AddReplyCommentRequest;
import com.example.model.dto.PageRequest;
import com.example.model.entity.Comment;
import com.example.model.entity.LikeDislike;
import com.example.model.entity.ReplyComment;
import com.example.model.entity.Wxuser;
import com.example.model.vo.PageVO;
import com.example.model.vo.ReplyCommentVO;
import com.example.service.ReplyCommentService;
import com.example.mapper.ReplyCommentMapper;
import com.example.utils.AccountHolder;
import com.example.utils.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author L
* @description 针对表【reply_comment】的数据库操作Service实现
* @createDate 2023-09-01 11:52:24
*/
@Service
@Transactional
public class ReplyCommentServiceImpl extends ServiceImpl<ReplyCommentMapper, ReplyComment>
    implements ReplyCommentService{

    @Resource
    ReplyCommentMapper replyCommentMapper;
    @Resource
    WxuserMapper wxuserMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    LikeDislikeMapper likeDislikeMapper;
    @Override
    public Result addReplyComment(AddReplyCommentRequest addReplyCommentRequest) {

        Wxuser user = AccountHolder.getUser();
        ReplyComment replyComment = new ReplyComment();
        BeanUtils.copyProperties(addReplyCommentRequest,replyComment);

        replyComment.setUserId(user.getId());
        replyComment.setReplyId("replyComment:"+ RandomUtil.generateRandomString(16));
        replyComment.setLikes(0);
        replyComment.setDislikes(0);

        save(replyComment);

        Comment comment = commentMapper.selectById(addReplyCommentRequest.getCommentId());

        comment.setSubCommentCount(comment.getSubCommentCount()+1);

        commentMapper.updateById(comment);

        return Result.success();
    }

    @Override
    public Result getReplyComment(String commentId, PageRequest pageRequest) {
        QueryWrapper<ReplyComment> replyCommentQueryWrapper = new QueryWrapper<>();
        replyCommentQueryWrapper.eq("comment_id",commentId).orderByDesc("updated_at").orderByDesc("likes");

        Page<ReplyComment> replyCommentPage = replyCommentMapper.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), replyCommentQueryWrapper);

        List<ReplyComment> replyComments = replyCommentPage.getRecords();

        List<ReplyCommentVO> replyCommentVOS=replyComments.stream().map(replyComment -> new ReplyCommentVO(
                replyComment.getReplyId(),
                replyComment.getUserId(),
                replyComment.getCommentId(),
                replyComment.getContent(),
                replyComment.getLikes(),
                replyComment.getDislikes(),
                replyComment.getCreatedAt(),
                wxuserMapper.selectById(replyComment.getUserId()).getAvatar(),
                wxuserMapper.selectById(replyComment.getUserId()).getUserName()
        )).toList();



        return Result.success(new PageVO<ReplyCommentVO>(replyCommentVOS,replyCommentPage.getTotal(),replyCommentPage.getSize(),replyCommentPage.getCurrent()));
    }

    @Override
    public Result deleteReplyComment(String replyId) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<ReplyComment> replyCommentQueryWrapper = new QueryWrapper<>();
        replyCommentQueryWrapper.eq("user_id",user.getId()).eq("reply_id",replyId);

        ReplyComment replyComment = getOne(replyCommentQueryWrapper);

        if (replyComment==null){
            throw new RuntimeException("该评论并非你发布的或者评论id错误!");
        }

        replyCommentMapper.deleteById(replyId);

        QueryWrapper<LikeDislike> commentId = new QueryWrapper<LikeDislike>().eq("comment_id", replyId);

        likeDislikeMapper.delete(commentId);

        return Result.success();
    }
}




