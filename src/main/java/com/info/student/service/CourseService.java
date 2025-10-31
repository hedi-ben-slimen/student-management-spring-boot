package com.info.student.service;

import com.info.student.model.Course;
import java.util.List;

public interface CourseService {
    Course create(String courseName, String department);
    Course getById(Long id);
    List<Course> list();
    Course update(Long id, String courseName, String department);
    void delete(Long id);
}