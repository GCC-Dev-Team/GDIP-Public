package com.example.controller;

import com.example.anno.AdminLogin;
import com.example.anno.NoNeedLogin;
import com.example.common.Result;
import com.example.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;
    /**
     * 增加类别
     *
     * @param categoryName 类别名称
     * @return
     */
    @AdminLogin
    @PostMapping("/addCategory")
    public Result addCategory(@NotNull String categoryName) {

        return categoryService.addCategory(categoryName);
    }

    /**
     * 所有类别
     */
    @NoNeedLogin
    @PostMapping("/allCategory")
    public Result showAllCategory() {
        return categoryService.showAllCategory();
    }

    /**
     * 根据id查询到名字
     * @param categoryId
     * @return
     */
    @GetMapping("/categoryName")
    public String getCategoryName(String categoryId){

        return categoryService.getNameOfCategory(categoryId);
    }

}
