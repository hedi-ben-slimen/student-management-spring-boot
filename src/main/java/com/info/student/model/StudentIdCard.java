package com.info.student.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student_id_card")
public class StudentIdCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id_card", nullable = false)
  private Long id;

  @Column(name = "cardNumber", nullable = false, columnDefinition = "TEXT", unique = true)
  private String cardNumber;

  @OneToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;
}
