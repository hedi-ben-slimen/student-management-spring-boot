package com.info.student.repository;

import com.info.student.model.EnrolementId;
import com.info.student.model.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolementRepository extends JpaRepository<Enrolment, EnrolementId> {
}
