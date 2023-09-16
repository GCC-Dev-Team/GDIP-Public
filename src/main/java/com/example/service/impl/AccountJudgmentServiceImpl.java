package com.example.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.service.AccountJudgmentService;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
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

    @Override
    public Boolean judgeIsAccountThree(String schoolId, String password) {
        return null;
    }

//    public static String des_encrypt_password(String password) {
//        // 密钥
//        String key = "zhxy-pc1";
//        try {
//            // 创建DESKeySpec对象并设置密钥
//            DESKeySpec desKey = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
//            // 创建SecretKeyFactory对象并指定算法为DES
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            // 生成SecretKey对象
//            SecretKey securekey = keyFactory.generateSecret(desKey);
//            // 创建Cipher对象并指定算法为DES
//            Cipher cipher = Cipher.getInstance("DES");
//            // 初始化Cipher对象为加密模式，并使用密钥进行初始化
//            cipher.init(Cipher.ENCRYPT_MODE, securekey);
//            // 加密明文密码并获取结果字节数组
//            byte[] result = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
//            // 对结果进行Base64编码并返回字符串形式
//            return java.util.Base64.getEncoder().encodeToString(result);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


//    public static String login_app(String username, String password) {
//        // 创建URL对象和HttpURLConnection对象
//        URL urlObject;
//        HttpURLConnection connection;
//        OutputStream os;
//        // 标记用户数据是否存在
//        boolean userdata = false;
//        // 对密码进行加密
//        String pwd = des_encrypt_password(password);
//        // 请求的URL
//        String url = "https://zhxygateway.gzzhyc.cn/userApi/login";
//        // 创建JSON对象并设置请求的参数
//        JSONObject payload = new JSONObject();
//        payload.put("userName", username);
//        payload.put("passWord", pwd);
//        payload.put("passWordType", "0");
//        try {
//            // 创建URL对象并打开连接
//            urlObject = new URL(url);
//            connection = (HttpURLConnection) urlObject.openConnection();
//            // 设置请求方法为POST
//            connection.setRequestMethod("POST");
//            // 设置请求头信息
//            connection.setRequestProperty("Host", "zhxygateway.gzzhyc.cn");
//            connection.setRequestProperty("Content-Length", "90");
//            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//            connection.setRequestProperty("X-Real-Timestamp", "1");
//            connection.setRequestProperty("Appkey", "zhxy-pc");
//            // 允许向服务器写入数据
//            connection.setDoOutput(true);
//            // 获取输出流并写入请求参数
//            os = connection.getOutputStream();
//            os.write(payload.toString().getBytes());
//            os.flush();
//            // 检查HTTP响应状态码
//            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
//            }
//            // 读取服务器响应并构建响应字符串
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String output;
//            StringBuilder response = new StringBuilder();
//            while ((output = br.readLine()) != null) {
//                response.append(output);
//            }
//            // 断开连接
//            connection.disconnect();
//            // 解析响应字符串为JSON对象
//            JSONObject js = new JSONObject();
//            cn.hutool.json.JSONObject j = new cn.hutool.json.JSONObject(response.toString());
//            cn.hutool.json.JSONObject data =(cn.hutool.json.JSONObject) j.get("data");
//            System.out.println(data.getStr("user"));
//             //根据userdata标记判断返回的数据类型
//            if (!userdata) {
//
//                // 返回token字段的值
//                return js.toString();
//            } else {
//                // 返回完整的data字段的值
//                return js.toString();
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
