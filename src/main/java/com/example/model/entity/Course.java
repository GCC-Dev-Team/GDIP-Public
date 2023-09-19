package com.example.model.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "course")
public class Course {


    /**
     * 课程学分
     */
    private String courseCredits;

    /**
     * 课程属性
     */
    private String courseAttribute;

    /**
     * 课程名称
     */
    @MongoId
    private String courseName;

    /**
     * 上课时间
     */
    private List<String> classTime;

    /**
     * 上课地点
     */
    private String classLocation;

    /**
     * 上课校区
     */
    private String campus;

    /**
     * 上课日期
     */
    private String classDate;

    /**
     * 分组名 (如果有分组名的话)
     */
    private String groupName;

}