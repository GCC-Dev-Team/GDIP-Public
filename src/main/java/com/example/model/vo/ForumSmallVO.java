package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumSmallVO {

    /**
     * 帖子id
     */
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
     * 表层描述
     */

    private String surfaceDescription;

    /**
     * 浏览量
     */
    private String view;

    /**
     * 帖子的分类
     */
    private String category;
}
