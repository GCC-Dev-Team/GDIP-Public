package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.CreateProductRequest;
import com.example.model.dto.PageRequest;
import com.example.model.dto.UpdateProductRequest;
import com.example.model.entity.Product;
import com.example.model.entity.Wxuser;
import com.example.model.vo.PageVO;
import com.example.model.vo.ProductDescribeVO;
import com.example.model.vo.ProductSmallVo;
import com.example.service.ProductService;
import com.example.mapper.ProductMapper;
import com.example.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* @author L
* @description 针对表【product】的数据库操作Service实现
* @createDate 2023-08-09 00:55:29
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{
    @Resource
    ProductMapper productMapper;
    @Override
    public Result getProductSmallAll(PageRequest pageRequest) {

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();

        productQueryWrapper.orderByDesc("updated_time");


        return   Result.success(getSmallProductVos(pageRequest,productQueryWrapper));
    }

    public PageVO<ProductSmallVo> getSmallProductVos(PageRequest pageRequest, QueryWrapper<Product> productQueryWrapper) {
        Page<Product> productPage = productMapper.selectPage(
                new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()),
                productQueryWrapper
        );

        List<Product> records = productPage.getRecords();

        List<ProductSmallVo> productSmallVos = records.stream()
                .map(product -> new ProductSmallVo(
                        product.getProductId(),
                        product.getProductTitle(),
                        product.getProductPrice(),
                        product.getProductUnit(),
                        product.getFrontImage()
                ))
                .collect(Collectors.toList());

        return new PageVO<>(productSmallVos, productPage.getTotal(), productPage.getSize(), productPage.getCurrent());
    }

    @Override
    public Result getProductDescribe(String productId) {

        Product byId = this.getById(productId);

        ProductDescribeVO productDescribeVO = new ProductDescribeVO();

        BeanUtils.copyProperties(byId,productDescribeVO);

        return Result.success(productDescribeVO);
    }

    @Override
    public Result createProduct(CreateProductRequest createProductRequest) {
        Wxuser user = AccountHolder.getUser();

        Product product = new Product();

        product.setProductId("product:"+UUID.randomUUID());
        // Set productTitle
        product.setProductTitle(createProductRequest.getProductTitle());

// Set productPrice
        product.setProductPrice(createProductRequest.getProductPrice());

// Set productUnit
        product.setProductUnit(createProductRequest.getProductUnit());

// Set productDescription
        product.setProductDescription(createProductRequest.getProductDescription());

// Set frontImage
        product.setFrontImage(createProductRequest.getFrontImage());

// Set productImage
        product.setProductImage(createProductRequest.getProductImage());

// Set productAddress
        product.setProductAddress(createProductRequest.getProductAddress());

        product.setProductStatus(0);
        product.setFavoritesCount(0);
        product.setViewsCount(0);
        product.setPublisherId(user.getId());

        this.save(product);

        ProductDescribeVO productDescribeVO = new ProductDescribeVO();

        BeanUtils.copyProperties(product,productDescribeVO);

        return Result.success(productDescribeVO);
    }

    @Override
    public Result getMyProduct(PageRequest pageRequest) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("publisher_id",user.getId());

        return Result.success(getSmallProductVos(pageRequest,productQueryWrapper));
    }

    @Override
    public Result updateProduct(UpdateProductRequest updateProductRequest) {
        int temple=0;

        Product byId = getById(updateProductRequest.getProductId());

        Wxuser user = AccountHolder.getUser();
         if(!user.getId().equals(byId.getPublisherId())){

             return Result.failure(ResultCode.PARAM_IS_INVALID,"该活动并非你发布的!");
         }

         if(updateProductRequest.getProductDescription()!=null){

             byId.setProductDescription(updateProductRequest.getProductDescription());
             temple=temple+1;
         }

        if (updateProductRequest.getProductTitle() != null) {
            byId.setProductTitle(updateProductRequest.getProductTitle());

            temple=temple+1;
        }

        if (updateProductRequest.getProductPrice() != null) {
            byId.setProductPrice(updateProductRequest.getProductPrice());

            temple=temple+1;
        }

        if (updateProductRequest.getProductUnit() != null) {
            byId.setProductUnit(updateProductRequest.getProductUnit());

            temple=temple+1;
        }

        if (updateProductRequest.getProductImage() != null) {
            byId.setProductImage(updateProductRequest.getProductImage());

            temple=temple+1;
        }

        if (updateProductRequest.getFrontImage() != null) {
            byId.setFrontImage(updateProductRequest.getFrontImage());
            temple=temple+1;
        }

        if (updateProductRequest.getProductAddress() != null) {
            byId.setProductAddress(updateProductRequest.getProductAddress());
            temple=temple+1;
        }

        if(temple>0){

            save(byId);
        }

        ProductDescribeVO productDescribeVO = new ProductDescribeVO();

        BeanUtils.copyProperties(byId,productDescribeVO);

        return Result.success(productDescribeVO);
    }

    @Override
    public Result deleteProduct(String productId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_id",productId).eq("publisher_id",user.getId());

        Product one = getOne(productQueryWrapper);

        if(one==null){

            throw new RuntimeException("查不到活动是该账号的!");
        }

        productMapper.deleteById(one);

        return Result.success();
    }
}




