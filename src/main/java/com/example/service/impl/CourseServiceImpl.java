package com.example.service.impl;

import com.example.config.CourseProperties;
import com.example.model.entity.Course;
import com.example.model.entity.Wxuser;
import com.example.service.AccountJudgmentService;
import com.example.service.CourseService;
import com.example.utils.AccountHolder;
import com.example.utils.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Resource
    MongoTemplate mongoTemplate;
    @Resource
    CourseProperties courseProperties;
    @Resource
    AccountJudgmentService accountJudgmentService;

    @Override
    public String getMyCourse() {
        Wxuser user = AccountHolder.getUser();

        String password = user.getPasswordJw();

        if (password == null) {
            throw new RuntimeException("出现错误，你未登录或绑定学号");
        }

        Course course = mongoTemplate.findById(password, Course.class);

        if (course == null) {

            return getCourseAllByPost(user.getStudentNumber(), password);
//             course = mongoTemplate.findById(password, Course.class);

        }

        return course.getMyCourse();
    }

    @Override
    public String getCourseAllByPost(@NotNull String schoolId, @NotNull String password) {
        try {
            // 创建 HttpURLConnection 对象
            HttpURLConnection connection = (HttpURLConnection) new URL(courseProperties.getCourseAddress()).openConnection();

            // 设置请求方法为 POST
            connection.setRequestMethod("POST");

            // 设置请求头部信息
            connection.setRequestProperty("Content-Type", "application/json");

            // 启用输入输出流
            connection.setDoOutput(true);


            // 构建请求体数据
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode requestBody = objectMapper.createObjectNode()
                    .put("loginid", schoolId)
                    .put("password", password)
                    .put("time", DateUtils.dateToString(new Date(), DateUtils.COURSE_DATE));

            // 将请求体数据写入输出流
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                objectMapper.writeValue((OutputStream) wr, requestBody);
            }

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应数据并解析为 JSON
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                    ObjectMapper responseMapper = new ObjectMapper();
                    JsonNode jsonResponse = responseMapper.readTree(in);
//

                    JsonNode dataNode = jsonResponse.get("data");
                    if (dataNode != null) {
                        JsonNode coursesNode = dataNode.get("courses");
                        if (coursesNode != null) {
                            String prettyString = coursesNode.toPrettyString();

                            Course myCode = new Course(schoolId, prettyString);
                            mongoTemplate.save(myCode);

                            return prettyString;
                        } else {
                            throw new RuntimeException("coursesNode 为 null");
                            // 处理 coursesNode 为 null 的情况
                        }
                    } else {
                         throw new RuntimeException("dataNode 为 null");
                    }

                }
            } else {
                // 处理请求失败的情况
                System.err.println("HTTP Request Failed with response code: " + responseCode);

                // 关闭连接
                connection.disconnect();
                throw new RuntimeException("HTTP Request Failed with response code:" + responseCode);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolId;
    }
}
