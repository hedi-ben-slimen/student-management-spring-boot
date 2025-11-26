package com.info.student.service;

import com.info.student.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourse();

    Optional<Course> getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course updatedCourse);

    void deleteCourse(Long id);
}
