package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSmallVo {
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
     *商品的表面图片
     */

    private String frontImage;

}
