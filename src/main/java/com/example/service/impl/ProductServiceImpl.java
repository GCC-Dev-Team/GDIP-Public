package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.mapper.PaymentMapper;
import com.example.mapper.WxuserMapper;
import com.example.model.dto.BalanceReceiveAndPayDTO;
import com.example.model.dto.CreateProductRequest;
import com.example.model.dto.PageRequest;
import com.example.model.dto.UpdateProductRequest;
import com.example.model.entity.Payment;
import com.example.model.entity.Product;
import com.example.model.entity.Wxuser;
import com.example.model.vo.BuySmallVO;
import com.example.model.vo.PageVO;
import com.example.model.vo.ProductDescribeVO;
import com.example.model.vo.ProductSmallVo;
import com.example.service.*;
import com.example.mapper.ProductMapper;
import com.example.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author L
 * @description 针对表【product】的数据库操作Service实现
 * @createDate 2023-08-09 00:55:29
 */
@Service
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

    @Resource
    BalanceRecordsService balanceRecordsService;
    @Resource
    ProductMapper productMapper;
    @Resource
    PaymentMapper paymentMapper;

    @Resource
    WxuserMapper wxuserMapper;

    @Resource
    ProductPayOwnService productPayOwnService;
    @Resource
    FavoritesService favoritesService;
    @Resource
    FollowersService followersService;
    @Resource
    AddressService addressService;

    @Override
    public Result getProductSmallAll(PageRequest pageRequest) {

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();

        productQueryWrapper.orderByDesc("updated_time")
                .eq("product_status", 0);

        return Result.success(getSmallProductVos(pageRequest, productQueryWrapper));
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
                        product.getFrontImage(),
                        product.getProductDescription(),
                        product.getProductStatus()
                ))
                .collect(Collectors.toList());

        return new PageVO<>(productSmallVos, productPage.getTotal(), productPage.getSize(), productPage.getCurrent());
    }

    @Override
    public Result getProductDescribe(String productId) {



        Product byId = this.getById(productId);

        ProductDescribeVO productDescribeVO = new ProductDescribeVO();

        BeanUtils.copyProperties(byId, productDescribeVO);

        addAttributeInfo(productDescribeVO);

        byId.setViewsCount(byId.getViewsCount()+1);

        updateById(byId);
        return Result.success(productDescribeVO);
    }

    public void addAttributeInfo(ProductDescribeVO productDescribeVO){
        //获取当前线程的用户
        Wxuser user = AccountHolder.getUser();
        //获取发布者的名字和头像
        productDescribeVO.setPublisherAvatar(wxuserMapper.selectById(productDescribeVO.getPublisherId()).getAvatar());
        productDescribeVO.setPublisherName(wxuserMapper.selectById(productDescribeVO.getPublisherId()).getUserName());
        //获取我是否收藏了
        productDescribeVO.setCollectionRecord(favoritesService.judgeFavorite(productDescribeVO.getProductId(), user.getId()));
        //获取我是否关注了
        productDescribeVO.setFollowerRecord(followersService.judgeFollower(user.getId(),productDescribeVO.getPublisherId()));
    }

    @Override
    public Result createProduct(CreateProductRequest createProductRequest) {
        Wxuser user = AccountHolder.getUser();

        Product product = new Product();

        product.setProductId("product:" + UUID.randomUUID());

        BeanUtils.copyProperties(createProductRequest,product);

        product.setProductStatus(0);
        product.setFavoritesCount(0);
        product.setViewsCount(0);
        product.setPublisherId(user.getId());
        product.setProductAddress(addressService.getAllAddressDescribeById(createProductRequest.getProductAddress()));

        this.save(product);

        ProductDescribeVO productDescribeVO = new ProductDescribeVO();

        BeanUtils.copyProperties(product, productDescribeVO);

        addAttributeInfo(productDescribeVO);

        return Result.success(productDescribeVO);
    }

    @Override
    public Result getMyProduct(PageRequest pageRequest) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("publisher_id", user.getId()).orderByDesc("updated_time");

        return Result.success(getSmallProductVos(pageRequest, productQueryWrapper));
    }

    @Override
    public Result updateProduct(UpdateProductRequest updateProductRequest) {
        int temple = 0;

        //如果已经拍下未支付或者成功支付的不能修改
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_status", 0).eq("product_id", updateProductRequest.getProductId());

        Product one = getOne(productQueryWrapper);

        Wxuser user = AccountHolder.getUser();
        if (!user.getId().equals(one.getPublisherId())) {

            return Result.failure(ResultCode.PRODUCT_AMEND_ERROR);
        }

        if (updateProductRequest.getProductDescription() != null) {

            one.setProductDescription(updateProductRequest.getProductDescription());
            temple = temple + 1;
        }

        if (updateProductRequest.getProductTitle() != null) {
            one.setProductTitle(updateProductRequest.getProductTitle());

            temple = temple + 1;
        }

        if (updateProductRequest.getProductPrice() != null) {
            one.setProductPrice(updateProductRequest.getProductPrice());

            temple = temple + 1;
        }

        if (updateProductRequest.getProductUnit() != null) {
            one.setProductUnit(updateProductRequest.getProductUnit());

            temple = temple + 1;
        }

        if (updateProductRequest.getProductImage() != null) {
            one.setProductImage(updateProductRequest.getProductImage());

            temple = temple + 1;
        }

        if (updateProductRequest.getFrontImage() != null) {
            one.setFrontImage(updateProductRequest.getFrontImage());
            temple = temple + 1;
        }

        if (updateProductRequest.getProductAddress() != null) {
            //现在拿到的是代码

            one.setProductAddress(addressService.getAllAddressDescribeById(updateProductRequest.getProductAddress()));
            temple = temple + 1;
        }

        if (temple > 0) {

            updateById(one);
        }

        ProductDescribeVO productDescribeVO = new ProductDescribeVO();

        BeanUtils.copyProperties(one, productDescribeVO);

        addAttributeInfo(productDescribeVO);

        return Result.success(productDescribeVO);
    }

    @Override
    public Result deleteProduct(String productId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_id", productId).eq("publisher_id", user.getId()).eq("product_status",0);

        Product one = getOne(productQueryWrapper);

        if (one == null) {

           return Result.failure(ResultCode.SYSTEM_ERROR,"删除错误，该商品并非正常上架中，已拍或者退款中!");
        }

        productMapper.deleteById(one);

        favoritesService.deleteFavoriteByProductId(productId);

        return Result.success();
    }



    @Override
    public Result getMyBuy() {
        Wxuser user = AccountHolder.getUser();
        String userId = user.getId();

        List<Payment> payments = paymentMapper.selectList(new QueryWrapper<Payment>().eq("payer", user.getId()).like("product_id", "product:"));
        List<Product> buysByUserId=new ArrayList<>();
        for (Payment payment : payments) {

            Product product = productMapper.selectById(payment.getProductId());

            buysByUserId.add(product);
        }
        //有参考的价值，好好理解这个过滤器stream
        List<BuySmallVO> productSmallVos = buysByUserId.stream()
                .map(product -> new BuySmallVO(
                        product.getProductId(),
                        product.getProductTitle(),
                        product.getProductPrice(),
                        product.getProductUnit(),
                        product.getFrontImage(),
                        product.getProductStatus()
                ))
                .collect(Collectors.toList());
        return Result.success(productSmallVos);
    }

    /**
     * 确认收货
     * @param productId
     * @return
     */
    @Override
    public Result Receive(String productId) {


        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<Product>().eq("product_id", productId).eq("product_status", 3);

        Product product = productMapper.selectOne(productQueryWrapper);

        if(product==null){
            return Result.failure(ResultCode.SYSTEM_ERROR,"该商品已确认收货或改商品不是你的");
        }

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<Payment>().eq("product_id",product.getProductId());

        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);
        if (!payment.getRecipient().equals(user.getId())){

             return Result.failure(ResultCode.SYSTEM_ERROR,"你不是收货用户");
        }

        product.setProductStatus(1);
        updateById(product);

        //打款给卖家钱包余额
        BalanceReceiveAndPayDTO balanceReceiveAndPayDTO = new BalanceReceiveAndPayDTO(0,
                product.getProductPrice(),
                payment.getOutTradeNo());
        balanceRecordsService.payOrReceive(balanceReceiveAndPayDTO);

        return Result.success("收货成功");
    }

    /**
     * 申请退款(买家)（payment表）
     * @param productId
     * @return
     */
    @Override
    public Result refund(String productId) {

        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Payment> paymentQueryWrapper = new QueryWrapper<>();
        paymentQueryWrapper.eq("product_id",productId).eq("payer",user.getId()).eq("status_number",1);
        Payment payment = paymentMapper.selectOne(paymentQueryWrapper);
        if (payment==null){
            log.error("支付查询错误，请联系管理员!");
            throw new RuntimeException("支付查询错误，请联系管理员!");
        }

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("product_id",productId).eq("product_status",3);
        Product product = productMapper.selectOne(productQueryWrapper);

        if (product==null){
            log.error("申请退款接口商品出现错误");
            throw new RuntimeException("申请退款商品查询出错!");
        }

        //改变商品的状态
        product.setProductStatus(4);
        updateById(product);

        return Result.success("请联系卖家同意退款!");
    }

    /**
     * 同意退款
     * @param productId
     * @return
     */

    @Override
    public Result agreeRefund(String productId) {

        Boolean refund = productPayOwnService.refund(productId);

        return Result.success(refund);//返回TURE
    }
}




