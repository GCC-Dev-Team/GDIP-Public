package com.example.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     *商品的收藏量
     */

    private Integer favoritesCount;

    /**
     *商品的浏览量
     */
    private Integer viewsCount;

}
