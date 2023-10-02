package com.example.service;

import com.example.common.Result;
import com.example.model.dto.PageRequest;
import com.example.model.entity.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【favorites】的数据库操作Service（商品的）
* @createDate 2023-08-09 00:55:38
*/
public interface FavoritesService extends IService<Favorites> {

    Result favoriteItems(@NotNull String productId);

    Result updateFavorite(@NotNull String productId);

    Result getMyFavorite(@NotNull @RequestBody PageRequest pageRequest);

    /**
     * 判断是否收藏了,0是没有收藏的，1是收藏的了
     * @param productId
     * @param userId
     * @return
     */
    Integer judgeFavorite(@NotNull String productId, @NotNull String userId);

    /**
     * 删除商品时，删除的收藏记录
     */
    Boolean deleteFavoriteByProductId(@NotNull String productId);
}
