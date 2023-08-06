package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Category;
import com.example.service.CategoryService;
import com.example.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-08-06 12:43:45
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




