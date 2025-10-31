package com.info.student.service.Impl;

import com.info.student.model.Course;
import com.info.student.repository.CourseRepository;
import com.info.student.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public Course create(String courseName, String department) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setDepartment(department);
        return courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    @Override
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course update(Long id, String courseName, String department) {
        Course course = getById(id);
        course.setCourseName(courseName);
        course.setDepartment(department);
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Course course = getById(id);
        courseRepository.delete(course);
    }
}