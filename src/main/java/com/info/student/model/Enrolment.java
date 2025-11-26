package com.info.student.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrolment")
@Data
public class Enrolment {

  @EmbeddedId private EnrolementId id;

  @ManyToOne
  @MapsId("student_id")
  @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "enrolment_student_id_fk"))
  private Student student;

  @ManyToOne
  @MapsId("course_id")
  @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "enrolment_course_id_fk"))
  private Course course;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime enrolmentDate;
}
