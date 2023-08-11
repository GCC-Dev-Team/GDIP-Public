package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.entity.Favorites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author L
* @description 针对表【favorites】的数据库操作Mapper
* @createDate 2023-08-09 00:55:38
* @Entity com.example.model.entity.Favorites
*/
public interface FavoritesMapper extends BaseMapper<Favorites> {

    //非常值得学习分页查询的地方
    @Select("SELECT product.*FROM favorites LEFT JOIN product ON favorite_product_id=product_id WHERE favorite_user_id=#{userId}")
    IPage<Product> getMyFavoritesByUserId(@Param("userId") String userId, Page<Product> page);//,@Param("product")Product product
}




