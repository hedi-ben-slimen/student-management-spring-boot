package com.info.student.service;

import com.info.student.model.Enrolment;
import com.info.student.model.EnrolementId;
import java.util.List;

public interface EnrolmentService {
    Enrolment create(Long studentId, Long courseId);
    Enrolment getById(EnrolementId id);
    List<Enrolment> list();
    List<Enrolment> getByStudentId(Long studentId);
    List<Enrolment> getByCourseId(Long courseId);
    Enrolment update(Long studentId, Long courseId);
    void delete(Long studentId, Long courseId);
}