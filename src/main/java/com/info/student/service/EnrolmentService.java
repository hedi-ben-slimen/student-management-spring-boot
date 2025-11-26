package com.info.student.service;


import com.info.student.model.Enrolment;

public interface EnrolmentService {
    Enrolment enrollStudent(Long studentId, Long courseId);


    void exitCourse(Long studentId, Long courseId);
}
