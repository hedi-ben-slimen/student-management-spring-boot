package com.info.student.service.Impl;

import com.info.student.model.*;
import com.info.student.repository.CourseRepository;
import com.info.student.repository.EnrolmentRepository;
import com.info.student.repository.StudentRepository;
import com.info.student.service.EnrolmentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Enrolment create(Long studentId, Long courseId, LocalDateTime enrolmentDate) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        EnrolementId id = new EnrolementId(studentId, courseId);
        if (enrolmentRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student is already enrolled in this course");
        }

        Enrolment enrolment = new Enrolment();
        enrolment.setId(id);
        enrolment.setStudent(student);
        enrolment.setCourse(course);
        enrolment.setEnrolmentDate(enrolmentDate);

        return enrolmentRepo.save(enrolment);
    }

    @Override
    public Enrolment getById(EnrolementId id) {
        return enrolmentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrolment not found"));
    }

    @Override
    public List<Enrolment> list() {
        return enrolmentRepo.findAll();
    }

    @Override
    public List<Enrolment> getByStudentId(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return enrolmentRepo.findByStudent(student);
    }

    @Override
    public List<Enrolment> getByCourseId(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        return enrolmentRepo.findByCourse(course);
    }

    @Override
    @Transactional
    public Enrolment update(Long studentId, Long courseId, LocalDateTime enrolmentDate) {
        EnrolementId id = new EnrolementId(studentId, courseId);
        Enrolment enrolment = getById(id);
        enrolment.setEnrolmentDate(enrolmentDate);
        return enrolmentRepo.save(enrolment);
    }

    @Override
    @Transactional
    public void delete(Long studentId, Long courseId) {
        EnrolementId id = new EnrolementId(studentId, courseId);
        Enrolment enrolment = getById(id);
        enrolmentRepo.delete(enrolment);
    }
}