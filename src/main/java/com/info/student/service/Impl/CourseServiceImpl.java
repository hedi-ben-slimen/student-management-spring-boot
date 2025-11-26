package com.info.student.service.Impl;

import com.info.student.model.Course;
import com.info.student.repository.CourseRepository;
import com.info.student.service.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(existingCourse -> {
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDepartment(updatedCourse.getDepartment());
            return courseRepository.save(existingCourse);
        }).orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id " + id);
        }
        courseRepository.deleteById(id);

    }
}
