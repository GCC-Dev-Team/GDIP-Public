package com.example.service;

import com.example.common.Result;
import com.example.model.dto.CreateProductRequest;
import com.example.model.dto.PageRequest;
import com.example.model.dto.UpdateProductRequest;
import com.example.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【product】的数据库操作Service
* @createDate 2023-08-09 00:55:29
*/
public interface ProductService extends IService<Product> {
    Result getProductSmallAll(@NotNull @RequestBody PageRequest pageRequest);

    Result getProductDescribe(@NotNull String productId);

    Result createProduct(@RequestBody @NotNull CreateProductRequest createProductRequest);

    Result getMyProduct(@RequestBody @NotNull PageRequest pageRequest);

    Result updateProduct(@RequestBody @NotNull UpdateProductRequest updateProductRequest);

    Result deleteProduct(@NotNull String productId);

   String updatePhoto(@NotNull MultipartFile file);

    Boolean deletePhoto(@NotNull String[] photoName);


    /**
     * 查看我的购买
     * @return
     */
    Result getMyBuy();

    /**
     * 确认收货
     * @param productId
     * @return
     */
    Result Receive(@NotNull String productId);

    /**
     * 买家申请退款
     * @param productId
     * @return
     */
    Result refund(@NotNull String productId);

    /**
     * 卖家同意退款
     * @param productId
     * @return
     */
    Result agreeRefund(@NotNull String productId);

}
