package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.mapper.CommentMapper;
import com.example.mapper.ReplyCommentMapper;
import com.example.model.entity.Comment;
import com.example.model.entity.LikeDislike;
import com.example.model.entity.ReplyComment;
import com.example.model.entity.Wxuser;
import com.example.service.LikeDislikeService;
import com.example.mapper.LikeDislikeMapper;
import com.example.utils.AccountHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
* @author L
* @description 针对表【like_dislike】的数据库操作Service实现
* @createDate 2023-09-02 10:16:14
*/
@Service
public class LikeDislikeServiceImpl extends ServiceImpl<LikeDislikeMapper, LikeDislike>
    implements LikeDislikeService{
    @Resource
    LikeDislikeMapper likeDislikeMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    ReplyCommentMapper replyCommentMapper;
    /**
     * 注意这张表的commentId是通用的，就是comment表的和reply表的一样，（reply的replyId）
     * @param commentId
     * @return
     */
    //赞和踩分开
    @Override
    public Result creatLike(String commentId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<LikeDislike> likeDislikeQueryWrapper = new QueryWrapper<>();

        likeDislikeQueryWrapper.eq("comment_id",commentId).eq("userId",user.getId()).eq("status",0);

        LikeDislike one = getOne(likeDislikeQueryWrapper);

        if (one!=null){
            //取消点赞
            likeDislikeMapper.delete(likeDislikeQueryWrapper);

            //赞数减少
            return Result.success( changeLikeOrDisLike(commentId,0,1));
        }

        LikeDislike likeDislike = new LikeDislike();

        likeDislike.setId("like:"+UUID.randomUUID());
        likeDislike.setUserid(user.getId());
        likeDislike.setCommentId(commentId);
        likeDislike.setStatus(0);
        //增加点赞
        changeLikeOrDisLike(commentId,0,0);
        likeDislikeMapper.insert(likeDislike);

        return Result.success();
    }


    //增加点赞数量和踩数量(change==0是是增加，1是减少)(o是喜欢，1是喜欢)
    Boolean changeLikeOrDisLike(String commentId,int status,int change){

        if (commentId.contains("comment:")){
            //根评论
            Comment comment = commentMapper.selectById(commentId);

            if(status==0){
                //喜欢
                Integer likes = comment.getLikes();

                comment.setLikes(likes+1);

            } else if (status==1) {
                //不喜欢
                Integer dislikes = comment.getDislikes();

                if (change==0){
                    comment.setDislikes(dislikes+1);
                } else if (change==1) {

                    comment.setDislikes(dislikes-1);
                }

            }

            commentMapper.updateById(comment);

            return Boolean.TRUE;
        }else {
            //子评论

            ReplyComment replyComment = replyCommentMapper.selectById(commentId);
            //喜欢数量
            if(status==0){

                Integer likes = replyComment.getLikes();

                replyComment.setLikes(likes+1);
            } else if (status == 1) {

                Integer dislikes = replyComment.getDislikes();

                if (change==0){

                    replyComment.setDislikes(dislikes+1);

                } else if (change==1) {

                    replyComment.setDislikes(dislikes-1);
                }
            }
            replyCommentMapper.updateById(replyComment);

            return Boolean.TRUE;

        }


    }


}




