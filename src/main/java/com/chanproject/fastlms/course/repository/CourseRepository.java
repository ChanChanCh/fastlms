package com.chanproject.fastlms.course.repository;


import com.chanproject.fastlms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {


}
