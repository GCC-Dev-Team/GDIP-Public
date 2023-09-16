package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.model.vo.CreateOrderVO;
import com.example.service.*;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 商品
 */
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

        //TODO 需要全部完成才能开发
    }
    /**
     * 查看我购买的订单（状态数字可以知道该订单是什么状态）
     * @return
     */
    @GetMapping("/myBuys")
    public Result getMyBuy(){
        return productService.getMyBuy();
    }

    //确认收货
    //产品的id号，需要token
    //确认收货后，钱会去到买家那（类似于闲鱼的模式）

    /**
     * 支付成功后，买家确认收货
     * @param productId
     * @return
     */
    @PostMapping("/Receive")
    public Result Receive(@NotNull String productId){
        return productService.Receive(productId);
        //TODO 需要企业对公账号
    }

    //支付成功后，申请退款（还没确认收货）//需要卖家同意？？？
    /**
     * 买家申请退款
     * @param productId
     * @return
     */
    @PutMapping("/refund")
    public Result refund(@NotNull String productId){

        return productService.refund(productId);
    }
    /**
     * 卖家同意退款
     * @param productId
     * @return
     */

    //卖家同意退款
    @PutMapping("/agreeRefund")
    public Result agreeRefund(@NotNull String productId){

        return productService.agreeRefund(productId);
    }

}
