package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "course")
@TypeAlias("Course")
public class Course {
    /**
     * 我的学号
     */
    @MongoId
    private String schoolId;

    /**
     * 我的课程
     */
    @Field("myCourse")
    private String myCourse;


}
