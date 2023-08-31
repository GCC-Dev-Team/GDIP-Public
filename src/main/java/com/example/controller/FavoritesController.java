package com.example.controller;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.service.FavoritesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Resource
    FavoritesService favoritesService;
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

}
