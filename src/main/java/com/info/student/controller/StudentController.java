package com.info.student.controller;

import com.info.student.model.Student;
import com.info.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService service;

  public StudentController(StudentService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Student create(@RequestBody Student student) {
    return service.create(
        student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge());
  }

  @GetMapping("/{id}")
  public Student getById(@PathVariable Long id) {
    return service.getById(id);
  }

  @GetMapping
  public List<Student> list() {
    return service.list();
  }

  @GetMapping("/by-email")
  public Optional<Student> getByEmail(@RequestParam String email) {
    return service.getByEmail(email);
  }

  @PutMapping("/{id}")
  public Student update(@PathVariable Long id, @RequestBody Student student) {
    return service.update(
        id, student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
