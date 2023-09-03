package com.example.service.impl;

import com.example.service.AccountJudgmentService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class AccountJudgmentServiceImpl implements AccountJudgmentService {

    private static final String ACCUNT_URL_GQ  = "http://cuuemo.cn:5000/login";
    @Resource
    RestTemplate restTemplate;
    @Override
    public Boolean judgeIsAccount(String schoolId, String password) {


        // 构建 HTTP 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建 JSON 请求体
        // 使用占位符将形参与 JSON 请求体联系起来
        String jsonBody = String.format("{\"loginid\": \"%s\", \"password\": \"%s\"}", schoolId, password);


        // 创建 HTTP 请求实体，将 JSON 请求体添加到请求中
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        // 发送 POST 请求并接收响应
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ACCUNT_URL_GQ, requestEntity, String.class);


        if (responseEntity.getStatusCode().value()==200){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
