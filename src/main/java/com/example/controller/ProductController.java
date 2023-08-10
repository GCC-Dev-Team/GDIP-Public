package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.CreateProductRequest;
import com.example.model.dto.PageRequest;
import com.example.model.dto.UpdateProductRequest;
import com.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    ProductService productService;

    /**
     * 获取所有的商品，小图
     * @param pageRequest
     * @return
     */
    @PostMapping("/getProductSnallAll")
    public Result getProductSmallAll(@NotNull @RequestBody PageRequest pageRequest){

        return productService.getProductSmallAll(pageRequest);
    }
    /**
     * 获取商品的信息（详细）
     */
    @GetMapping("/getProductDescribe")
    public Result getProductDescribe(@NotNull String productId){

        return productService.getProductDescribe(productId);
    }
    /**
     * 发布商品
     */
    @PostMapping("/product")
    public Result createProduct(@RequestBody @NotNull CreateProductRequest createProductRequest){

        return productService.createProduct(createProductRequest);
    }
    /**
     * 查看我发布的商品
     */
    @PostMapping("/myProduct")
    public Result getMyProduct(@RequestBody @NotNull PageRequest pageRequest){

        return productService.getMyProduct(pageRequest);
    }

    /**
     * 修改商品的信息
     * @param updateProductRequest
     * @return
     */

    @PutMapping("/product")
    public Result updateProduct(@RequestBody @NotNull UpdateProductRequest updateProductRequest){

        return productService.updateProduct(updateProductRequest);
    }

    /**
     * 删除商品
     * @param productId 根据productId
     * @return
     */

    @DeleteMapping("/product")
    public Result deleteProduct(@NotNull String productId){

        return productService.deleteProduct(productId);
    }

}
