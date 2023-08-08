package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Address;
import com.example.service.AddressService;
import com.example.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【address】的数据库操作Service实现
* @createDate 2023-08-09 00:55:43
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

}




