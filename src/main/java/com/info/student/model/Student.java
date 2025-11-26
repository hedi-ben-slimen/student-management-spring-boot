package com.info.student.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id", nullable = false)
  private Long id;

  @Column(name = "firstname", nullable = false, columnDefinition = "TEXT")
  private String firstName;

  @Column(name = "lastName", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
  private String email;

  @Column(name = "age", nullable = false, columnDefinition = "INT")
  private int age;

  @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private StudentIdCard studentIdCard;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  private List<Enrolment> enrolments;
}
