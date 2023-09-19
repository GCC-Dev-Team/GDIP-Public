package com.example.controller;

import com.example.model.entity.Course;
import com.example.repository.CourseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    CourseRepository courseRepository;
    @RequestMapping("/course")
    public String  get(){
        Course course = new Course();
        course.setCourseName("小力学java");
        course.setCourseAttribute("3");
        courseRepository.save(course);

        return  courseRepository.findAll().toString();
    }
}
