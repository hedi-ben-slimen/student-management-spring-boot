package com.info.student.repository;

import com.info.student.model.Enrolment;
import com.info.student.model.EnrolementId;
import com.info.student.model.Student;
import com.info.student.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, EnrolementId> {

    List<Enrolment> findByStudent(Student student);

    List<Enrolment> findByCourse(Course course);

    List<Enrolment> findByIdStudentId(Long studentId);

    List<Enrolment> findByIdCourseId(Long courseId);

    // Find a specific enrolment by studentId and courseId
    Optional<Enrolment> findByIdStudentIdAndIdCourseId(Long studentId, Long courseId);

}
