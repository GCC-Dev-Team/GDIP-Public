package com.example.mapper;

import com.example.model.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author L
* @description 针对表【product】的数据库操作Mapper
* @createDate 2023-08-09 00:55:29
* @Entity com.example.model.entity.Product
*/
public interface ProductMapper extends BaseMapper<Product> {

}




