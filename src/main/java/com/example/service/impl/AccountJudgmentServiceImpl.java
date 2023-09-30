package com.example.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.config.StudentVerifyProperties;
import com.example.service.AccountJudgmentService;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class AccountJudgmentServiceImpl implements AccountJudgmentService {

    @Resource
    StudentVerifyProperties studentVerifyProperties;
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
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(studentVerifyProperties.getVerifyAddress(), requestEntity, String.class);


        if (responseEntity.getStatusCode().value()==200){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }


}
