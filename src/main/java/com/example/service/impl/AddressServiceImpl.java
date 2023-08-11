package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.AddAddressRequest;
import com.example.model.dto.UpdateAddressRequest;
import com.example.model.entity.Address;
import com.example.model.entity.Wxuser;
import com.example.service.AddressService;
import com.example.mapper.AddressMapper;
import com.example.utils.AccountHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
* @author L
* @description 针对表【address】的数据库操作Service实现
* @createDate 2023-08-09 00:55:43
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{
    @Override
    public Result addAddress(AddAddressRequest addAddressRequest) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        QueryWrapper<Address> eq = addressQueryWrapper.eq("creator", user.getId()).eq("region_id", addAddressRequest.getAddressCode()).eq("detailed_address", addAddressRequest.getDescribe());

        Address one = getOne(eq);

        if (one!=null){
            return Result.failure(ResultCode.SYSTEM_ERROR,"你已经添加此地址");
        }

        Address address = new Address();

        address.setId("address:"+ UUID.randomUUID());
        address.setDetailedAddress(addAddressRequest.getDescribe());
        address.setCreator(user.getId());
        address.setRegionId(String.valueOf(addAddressRequest.getAddressCode()));

        save(address);

        return Result.success();
    }


    @Override
    public Result getMyAllAddress() {
        return null;
    }

    @Override
    public Result updateMyAddress(UpdateAddressRequest updateAddressId) {
        return null;
    }//TODO

    @Override
    public Result deleteAddress(String addressId) {
        return null;
    }//TODO
}




