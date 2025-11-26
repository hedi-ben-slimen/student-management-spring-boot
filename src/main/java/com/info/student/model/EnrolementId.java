package com.info.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class EnrolementId {

  @Column(name = "student_id")
  private Long studentId;

  @Column(name = "course_id")
  private Long courseId;

  public EnrolementId(Long studentId, Long courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

  public EnrolementId() {}

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    EnrolementId that = (EnrolementId) obj;

    return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, courseId);
  }
}
