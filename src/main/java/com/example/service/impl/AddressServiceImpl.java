package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.model.dto.AddAddressRequest;
import com.example.model.dto.UpdateAddressRequest;
import com.example.model.entity.Address;
import com.example.model.entity.Wxuser;
import com.example.model.vo.AddressVO;
import com.example.service.AddressService;
import com.example.mapper.AddressMapper;
import com.example.utils.AccountHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author L
 * @description 针对表【address】的数据库操作Service实现
 * @createDate 2023-08-09 00:55:43
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {
    @Resource
    AddressMapper addressMapper;

    @Override
    public Result addAddress(AddAddressRequest addAddressRequest) {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        QueryWrapper<Address> eq = addressQueryWrapper.eq("creator", user.getId()).eq("region_id", addAddressRequest.getAddressCode()).eq("detailed_address", addAddressRequest.getDescribe());

        Address one = getOne(eq);

        if (one != null) {
            return Result.failure(ResultCode.SYSTEM_ERROR, "你已经添加此地址");
        }

        Address address = new Address();

        address.setId("address:" + UUID.randomUUID());
        address.setDetailedAddress(addAddressRequest.getDescribe());
        address.setCreator(user.getId());
        address.setRegionId(String.valueOf(addAddressRequest.getAddressCode()));

        save(address);

        return Result.success();
    }


    @Override
    public Result getMyAllAddress() {
        Wxuser user = AccountHolder.getUser();

        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        addressQueryWrapper.eq("creator", user.getId());

        List<Address> addresses = addressMapper.selectList(addressQueryWrapper);

        List<AddressVO> addressVOS = new ArrayList<>();

        for (Address address : addresses) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(address, addressVO);
            addressVOS.add(addressVO);
        }

        return Result.success(addressVOS);
    }

    @Override
    public Result updateMyAddress(UpdateAddressRequest updateAddressId) {
       int temple=0;

        String addressId = updateAddressId.getAddressId();

        Address byId = getById(addressId);

        if (updateAddressId.getAddressCode()!=null){

            byId.setRegionId(String.valueOf(updateAddressId.getAddressCode()));

            temple=temple+1;
        }

        if(updateAddressId.getDescribe()!=null){

            byId.setDetailedAddress(updateAddressId.getDescribe());

            temple=temple+1;
        }

        if(temple>0){

            save(byId);
        }

        return Result.success();
    }

    @Override
    public Result deleteAddress(String addressId) {

        Address byId = getById(addressId);

        Wxuser user = AccountHolder.getUser();

        boolean equals = byId.getCreator().equals(user.getId());

        if(equals){

            addressMapper.deleteById(byId);

            return Result.success();
        }
        return Result.failure(ResultCode.PARAM_IS_INVALID,"此地址删除失败，非本账号的");
    }
}




