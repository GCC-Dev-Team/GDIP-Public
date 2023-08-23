package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumDescribeVO {

    private String id;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子小图
     */
    private String surfaceImage;

    /**
     * 帖子发布者
     */
    private String publisher;

    /**
     * 帖子的文件url
     */
    private String mdFileUrl;

    /**
     * 帖子的浏览量
     */
    private Integer views;

    /**
     * 帖子的分类
     */
    private String category;

    private String avatar;

    private String nickName;
}
