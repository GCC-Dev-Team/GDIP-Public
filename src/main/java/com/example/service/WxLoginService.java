package com.example.service;

import com.example.common.Result;
import com.example.model.dto.WxLoginRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface WxLoginService {
    Result wxLogin(@RequestBody WxLoginRequest wxLoginRequest);
}
