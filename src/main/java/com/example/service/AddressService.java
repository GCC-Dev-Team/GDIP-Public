package com.example.service;

import com.example.common.Result;
import com.example.model.dto.AddAddressRequest;
import com.example.model.dto.UpdateAddressRequest;
import com.example.model.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【address】的数据库操作Service
* @createDate 2023-08-09 00:55:43
*/
public interface AddressService extends IService<Address> {

    Result addAddress(@NotNull @RequestBody AddAddressRequest addAddressRequest);

    Result getMyAllAddress();

    Result updateMyAddress(@NotNull @RequestBody UpdateAddressRequest updateAddressId);

    Result deleteAddress(@NotNull String addressId);
}
