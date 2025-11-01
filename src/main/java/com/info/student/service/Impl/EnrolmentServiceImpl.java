package com.info.student.service.Impl;

import com.info.student.Exception.ConfilctException;
import com.info.student.Exception.RessourceNotFoundException;
import com.info.student.model.*;
import com.info.student.repository.CourseRepository;
import com.info.student.repository.EnrolmentRepository;
import com.info.student.repository.StudentRepository;
import com.info.student.service.EnrolmentService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrolmentServiceImpl implements EnrolmentService {

    private final EnrolmentRepository enrolmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrolmentServiceImpl(EnrolmentRepository enrolmentRepo,
                              StudentRepository studentRepo,
                              CourseRepository courseRepo) {
        this.enrolmentRepo = enrolmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    @Transactional
    public Enrolment EnrollStudent(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RessourceNotFoundException("Student not found" + studentId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RessourceNotFoundException("Course not found"));

        EnrolementId id = new EnrolementId(studentId, courseId);
        if (enrolmentRepo.existsById(id)) {
            throw new ConfilctException("Enrolment already exists");
        }

        Enrolment enrolment = new Enrolment();
        enrolment.setId(id);
        enrolment.setStudent(student);
        enrolment.setCourse(course);

        LocalDateTime now = LocalDateTime.now();
        enrolment.setEnrolmentDate(now);

        return enrolmentRepo.save(enrolment);
    }

    @Override
    public Enrolment getById(EnrolementId id) {
        return enrolmentRepo.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Enrollment not found"));
    }

    @Override
    public List<Enrolment> list() {
        return enrolmentRepo.findAll();
    }

    @Override
    public List<Enrolment> getByStudentId(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RessourceNotFoundException("Student not found"));
        return enrolmentRepo.findByStudent(student);
    }

    @Override
    public List<Enrolment> getByCourseId(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RessourceNotFoundException("Course not found"));
        return enrolmentRepo.findByCourse(course);
    }

    @Override
    @Transactional
    public Enrolment updateEnrolment(Long studentId, Long courseId) {
        EnrolementId id = new EnrolementId(studentId, courseId);
        Enrolment enrolment = getById(id);
        LocalDateTime now = LocalDateTime.now();
        enrolment.setEnrolmentDate(now);
        return enrolmentRepo.save(enrolment);
    }

    @Override
    @Transactional
    public void ExitEnrollment(Long studentId, Long courseId) {
        EnrolementId id = new EnrolementId(studentId, courseId);
        Enrolment enrolment = getById(id);
        enrolmentRepo.delete(enrolment);
    }
}