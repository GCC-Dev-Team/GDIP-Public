package com.example.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品的标题
     */
    private String productTitle;

    /**
     * 商品的价格
     */
    private Double productPrice;

    /**
     * 商品的单位
     */
    private String productUnit;

    /**
     * 商品的描述
     */
    private String productDescription;

    /**
     * 商品的图片
     */
    private String productImage;

    /**
     * 商品的表面图片
     */
    private String frontImage;

    /**
     * 商品的地址
     */
    private String productAddress;
}
