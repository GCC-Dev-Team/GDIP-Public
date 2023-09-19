package com.example.repository;

import com.example.model.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Course findCourseByCourseName(String courseName);
}
