package com.example.controller;

import com.example.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    CourseService courseService;
    /**
     * 获取我的课程信息
     */
    @GetMapping
    public String getMyCourse(){

        return courseService.getMyCourse();
    }
    /**
     * 更新所有人课表信息（暂未开放）
     */


}
