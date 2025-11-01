package com.info.student.repository;

import com.info.student.model.Student;
import com.info.student.model.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, Long> {

    Optional<StudentIdCard> findByStudent(Student student);

    boolean existsByStudent(Student student);
}