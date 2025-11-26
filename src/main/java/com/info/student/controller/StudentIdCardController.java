package com.info.student.controller;

import com.info.student.model.StudentIdCard;
import com.info.student.service.StudentIdCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-id-cards")
public class StudentIdCardController {

  private final StudentIdCardService service;

  public StudentIdCardController(StudentIdCardService service) {
    this.service = service;
  }

  // Create without DTOs: pass params directly
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StudentIdCard create(@RequestParam Long studentId, @RequestParam String cardNumber) {
    return service.create(studentId, cardNumber);
  }

  @GetMapping("/{id}")
  public StudentIdCard getById(@PathVariable Long id) {
    return service.getById(id);
  }

  @GetMapping("/by-student/{studentId}")
  public StudentIdCard getByStudent(@PathVariable Long studentId) {
    return service.getByStudentId(studentId);
  }

  @PutMapping("/{id}")
  public StudentIdCard update(@PathVariable Long id, @RequestParam String cardNumber) {
    return service.updateCardNumber(id, cardNumber);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
