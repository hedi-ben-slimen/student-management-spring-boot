package com.info.student.controller;

import com.info.student.dto.EnrolmentRequest;
import com.info.student.model.Enrolment;
import com.info.student.service.EnrolmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrolments")
public class EnrolementController {

    private final EnrolmentService enrolmentService;

    public EnrolementController(EnrolmentService enrolmentService) {
        this.enrolmentService = enrolmentService;
    }

    @PostMapping
    public ResponseEntity<Enrolment> enrollStudent(@RequestBody EnrolmentRequest enrolment) {
        Enrolment enrolement = enrolmentService.enrollStudent(enrolment.getStudentId(), enrolment.getCourseId());
        return ResponseEntity.status(201).body(enrolement);

    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> exitCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        enrolmentService.exitCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}
