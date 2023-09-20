package com.example.repository;

import com.example.model.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

}
