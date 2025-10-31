package com.info.student.service;

import com.info.student.model.Enrolment;
import com.info.student.model.EnrolementId;
import java.time.LocalDateTime;
import java.util.List;

public interface EnrolmentService {
    Enrolment create(Long studentId, Long courseId, LocalDateTime enrolmentDate);
    Enrolment getById(EnrolementId id);
    List<Enrolment> list();
    List<Enrolment> getByStudentId(Long studentId);
    List<Enrolment> getByCourseId(Long courseId);
    Enrolment update(Long studentId, Long courseId, LocalDateTime enrolmentDate);
    void delete(Long studentId, Long courseId);
}