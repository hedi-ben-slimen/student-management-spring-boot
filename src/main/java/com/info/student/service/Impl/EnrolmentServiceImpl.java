package com.info.student.service.Impl;

import com.info.student.exception.ConflictException;
import com.info.student.exception.ResourceNotFoundException;
import com.info.student.model.Course;
import com.info.student.model.EnrolementId;
import com.info.student.model.Enrolment;
import com.info.student.model.Student;
import com.info.student.repository.CourseRepository;
import com.info.student.repository.EnrolementRepository;
import com.info.student.repository.StudentRepository;
import com.info.student.service.EnrolmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnrolmentServiceImpl implements EnrolmentService {

    private final EnrolementRepository enrolementRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrolmentServiceImpl(EnrolementRepository enrolementRepository,
                                StudentRepository studentRepository,
                                CourseRepository courseRepository) {
        this.enrolementRepository = enrolementRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrolment enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID " + courseId));

        EnrolementId enrolmentId = new EnrolementId(studentId, courseId);
        if (enrolementRepository.existsById(enrolmentId)) {
            throw new ConflictException("Student with ID " + studentId + " is already enrolled in Course with ID " + courseId);
        }

        Enrolment enrolement = new Enrolment();
        enrolement.setId(enrolmentId);
        enrolement.setStudent(student);
        enrolement.setCourse(course);
        enrolement.setEnrolmentDate(LocalDateTime.now());

        return enrolementRepository.save(enrolement);
    }


    @Override
    public void exitCourse(Long studentId, Long courseId) {
        EnrolementId enrolmentId = new EnrolementId(studentId, courseId);
        if (!enrolementRepository.existsById(enrolmentId)) {
            throw new ResourceNotFoundException("Enrolment not found for Student ID " + studentId + " and Course ID " + courseId);
        }
        enrolementRepository.deleteById(enrolmentId);
    }
}
