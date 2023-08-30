package com.example.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDescribeVO{
    /**
     * 商品id
     */

    private String productId;

    /**
     *商品标题
     */

    private String productTitle;

    /**
     *商品价格
     */

    private Double productPrice;

    /**
     *商品的单位
     */

    private String productUnit;

    /**
     * 商品的详细描述
     */
    private String productDescription;

    /**
     *商品的图片
     */

    private String productImage;

    /**
     *商品的地址
     */

    private String productAddress;

    /**
     *商品的发布者
     */

    private String publisherId;

    /**
     * 卖家名称
     */
    private String publisherName;

    /**
     * 头像
     */
    private String publisherAvatar;

    /**
     *商品的收藏量
     */

    private Integer favoritesCount;

    /**
     *商品的浏览量
     */
    private Integer viewsCount;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 收藏记录（0是收藏了，1是没有收藏）
     */
    private Integer collectionRecord;

    /**
     * 关注记录(0代表没有关注，1代表关注了)
     */
    private Integer followerRecord;

}
