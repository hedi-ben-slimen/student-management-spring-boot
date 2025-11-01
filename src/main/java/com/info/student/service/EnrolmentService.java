package com.info.student.service;

import com.info.student.model.Enrolment;
import com.info.student.model.EnrolementId;
import java.util.List;

public interface EnrolmentService {
    Enrolment EnrollStudent(Long studentId, Long courseId);
    Enrolment getById(EnrolementId id);
    List<Enrolment> list();
    List<Enrolment> getByStudentId(Long studentId);
    List<Enrolment> getByCourseId(Long courseId);
    Enrolment updateEnrolment(Long studentId, Long courseId);
    void ExitEnrollment(Long studentId, Long courseId);
}