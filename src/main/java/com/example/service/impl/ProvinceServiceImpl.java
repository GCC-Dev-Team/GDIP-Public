package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Province;
import com.example.service.ProvinceService;
import com.example.mapper.ProvinceMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【province】的数据库操作Service实现
* @createDate 2023-08-09 00:55:24
*/
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService{

}




