package com.example.service;

import com.example.common.Result;
import com.example.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【category】的数据库操作Service
* @createDate 2023-08-06 12:43:45
*/
public interface CategoryService extends IService<Category> {

    Result addCategory(@NotNull String categoryName);

    Result showAllCategory();

    String getNameOfCategory(String categoryId);
}
