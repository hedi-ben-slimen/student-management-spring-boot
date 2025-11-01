package com.info.student.controller;

import com.info.student.model.Enrolment;
import com.info.student.model.EnrolementId;
import com.info.student.service.EnrolmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrolments")
public class EnrolmentController {

    private final EnrolmentService service;

    public EnrolmentController(EnrolmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enrolment EnrollStudent(@RequestBody Enrolment enrolment) {
        return service.EnrollStudent(
            enrolment.getStudent().getId(),
            enrolment.getCourse().getId()
            
        );
    }

    @GetMapping("/{studentId}/{courseId}")
    public Enrolment getById(@PathVariable Long studentId,
                            @PathVariable Long courseId) {
        return service.getById(new EnrolementId(studentId, courseId));
    }

    @GetMapping
    public List<Enrolment> list() {
        return service.list();
    }

    @GetMapping("/student/{studentId}")
    public List<Enrolment> getByStudentId(@PathVariable Long studentId) {
        return service.getByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrolment> getByCourseId(@PathVariable Long courseId) {
        return service.getByCourseId(courseId);
    }

    @PutMapping("/{studentId}/{courseId}")
    public Enrolment updateEnrolment(@PathVariable Long studentId,
                           @PathVariable Long courseId,
                           @RequestBody Enrolment enrolment) {
        return service.updateEnrolment(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ExitEnrollment(@PathVariable Long studentId,
                      @PathVariable Long courseId) {
        service.ExitEnrollment(studentId, courseId);
    }
}