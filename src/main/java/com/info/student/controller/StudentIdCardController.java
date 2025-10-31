package com.info.student.controller;

import com.info.student.model.StudentIdCard;
import com.info.student.service.StudentIdCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-cards")
public class StudentIdCardController {

    private final StudentIdCardService service;

    public StudentIdCardController(StudentIdCardService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentIdCard create(@RequestBody StudentIdCard card) {
        return service.create(card.getCardNumber(), card.getStudent().getId());
    }

    @GetMapping("/{id}")
    public StudentIdCard getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/student/{studentId}")
    public StudentIdCard getByStudentId(@PathVariable Long studentId) {
        return service.getByStudentId(studentId);
    }

    @GetMapping
    public List<StudentIdCard> list() {
        return service.list();
    }

    @PutMapping("/{id}")
    public StudentIdCard update(@PathVariable Long id,
                              @RequestBody StudentIdCard card) {
        return service.update(id, card.getCardNumber());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}