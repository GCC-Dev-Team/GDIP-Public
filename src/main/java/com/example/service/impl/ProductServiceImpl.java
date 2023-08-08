package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Product;
import com.example.service.ProductService;
import com.example.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【product】的数据库操作Service实现
* @createDate 2023-08-09 00:55:29
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




