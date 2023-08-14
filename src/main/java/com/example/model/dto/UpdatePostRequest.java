package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {

    /**
     * 论坛文章的id
     */

    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * 表层图片连接
     */
    private String imageUrl;

    /**
     * 文件链接
     */
    private String mdUrl;
    /**
     * 类型id
     */
    private String categoryId;

    /**
     * 表层描述
     */
    private String surfaceDescription;
}
