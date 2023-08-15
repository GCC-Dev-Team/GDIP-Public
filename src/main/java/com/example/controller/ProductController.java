package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    ProductService productService;

    @Resource
    FavoritesService favoritesService;

    @Resource
    FollowersService followersService;

    @Resource
    ProvinceService provinceService;

    @Resource
    AddressService addressService;

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
        //还没有完工，因为关联的表
    }

    /**
     * 收藏商品
     * @param productId 商品id
     * @return
     */

    @PostMapping("/FavoriteItems")
    public Result favoriteItems(@NotNull String productId){

        return favoritesService.favoriteItems(productId);
    }

    /**
     * 取消收藏
     * @param productId
     * @return
     */
    @PutMapping("/FavoriteItems")
    public Result updateFavorite(@NotNull String productId){

        return favoritesService.updateFavorite(productId);
    }

    /**
     * 分页查询我的收藏
     * @param pageRequest
     * @return
     */
    //非常值得学习分页查询多表联查
    @PostMapping("/myFavorite")
    public Result getMyFavorite(@NotNull @RequestBody PageRequest pageRequest){

        return favoritesService.getMyFavorite(pageRequest);
    }

    //我的关注

    /**
     * 关注
     * @param beFollowerId
     * @return
     */

    @PostMapping("/Follower")
    public Result follower(@NotNull String beFollowerId){

        return followersService.follower(beFollowerId);
    }

    /**
     * 取消关注
     * @param beFollowerId
     * @return
     */
    @PutMapping("/Follower")
    public Result updateMyFollower(@NotNull String beFollowerId){

        return followersService.updateMyFollower(beFollowerId);
    }

    //查看我的关注

    /**
     * 查看我的关注用户
     * @param pageRequest
     * @return
     */
    @PostMapping("/myAllFollowers")
    public Result getMyALlFollower(@NotNull@RequestBody PageRequest pageRequest){
        return  followersService.getMyALlFollower(pageRequest);
    }

    //地址

    /**
     * 获取省份以及省份编号
     * @return
     */
    @GetMapping("/province")
    public Result getAllProVince(){

        return provinceService.getAllProVince();
    }

    /**
     * 获取城市
     * @param provinceId 省id
     * @return
     */
    @GetMapping("/city")
    public Result getAllCity(@NotNull String provinceId){

        return provinceService.getAllCity(provinceId);
    }

    /**
     * 获取区
     * @param provinceId 省id
     * @param cityId 市id
     * @return
     */
    @GetMapping("/area")
    public Result getAllArea(@NotNull String provinceId, @NotNull String cityId){

        return provinceService.getAllArea(provinceId,cityId);
    }

    /**
     * 获取镇/街道
     * @param provinceId 省id
     * @param cityId 市id
     * @param areaId 区id
     * @return
     */
    @GetMapping("/town")
    public Result getAllTown(@NotNull String provinceId,@NotNull String cityId, @NotNull String areaId){

        return provinceService.getAllTown(provinceId,cityId,areaId);
    }

    /**
     * 获取code（代表省市区镇）
     * @param provinceId 省id
     * @param cityId 市id
     * @param areaId 区id
     * @param townId 镇id
     * @return int （代表省市区镇）
     */

    @GetMapping("/addressCode")
    public Result getAddressCode(@NotNull String provinceId, @NotNull String cityId,@NotNull String areaId,@NotNull String townId){
        return provinceService.getAddressCode(provinceId,cityId,areaId,townId);
    }

    /**
     * 新增地址
     * @param addAddressRequest
     * @return
     */
    @PostMapping("/addAddress")
    public Result addAddress(@NotNull @RequestBody AddAddressRequest addAddressRequest){
        return addressService.addAddress(addAddressRequest);
    }

    /**
     * 获取我所有的地址
     * @return
     */

    @GetMapping("/myAddress")
    public Result getMyAllAddress(){
        return addressService.getMyAllAddress();
    }

    /**
     * 修改我的地址
     * @param
     * @return
     */

    @PutMapping("/myAddress")
    public Result updateMyAddress(@NotNull @RequestBody UpdateAddressRequest updateAddressId){
        return addressService.updateMyAddress(updateAddressId);
    }

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    @DeleteMapping("/myAddress")
    public Result deleteAddress(@NotNull String addressId){
        return addressService.deleteAddress(addressId);
    }




}
