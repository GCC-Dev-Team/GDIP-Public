package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.model.entity.Category;
import com.example.model.entity.Wxuser;
import com.example.model.vo.CategoryVO;
import com.example.service.CategoryService;
import com.example.mapper.CategoryMapper;
import com.example.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author L
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2023-08-06 12:43:45
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public Result addCategory(String categoryName) {

        Wxuser user = AccountHolder.getUser();

        String categoryId = "category:" + UUID.randomUUID();

        Category category = new Category();
        category.setId(categoryId);
        category.setCategoryName(categoryName);
        category.setAdder(user.getId());

        CategoryVO categoryVO = new CategoryVO();

        BeanUtils.copyProperties(category, categoryVO);

        return Result.success(categoryVO);
    }

    @Override
    public Result showAllCategory() {

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.isNotNull("id");
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);

        List<CategoryVO> categoryVOS = new ArrayList<>();

        for (Category categoryTemple : categories) {

            CategoryVO categoryVOTemple = new CategoryVO();

            BeanUtils.copyProperties(categoryTemple, categoryVOTemple);

            categoryVOS.add(categoryVOTemple);
        }

        return Result.success(categoryVOS);
    }
}




