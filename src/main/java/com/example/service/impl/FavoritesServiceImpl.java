package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Favorites;
import com.example.service.FavoritesService;
import com.example.mapper.FavoritesMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【favorites】的数据库操作Service实现
* @createDate 2023-08-09 00:55:38
*/
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites>
    implements FavoritesService{

}




