package com.info.student.service.Impl;

import com.info.student.model.Student;
import com.info.student.model.StudentIdCard;
import com.info.student.repository.StudentIdCardRepository;
import com.info.student.repository.StudentRepository;
import com.info.student.service.StudentIdCardService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentIdCardServiceImpl implements StudentIdCardService {

  private final StudentIdCardRepository cardRepo;
  private final StudentRepository studentRepo;

  public StudentIdCardServiceImpl(StudentIdCardRepository cardRepo, StudentRepository studentRepo) {
    this.cardRepo = cardRepo;
    this.studentRepo = studentRepo;
  }

  @Override
  @Transactional
  public StudentIdCard create(Long studentId, String cardNumber) {
    Student student =
        studentRepo
            .findById(studentId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

    if (student.getStudentIdCard() != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already has an ID card");
    }

    StudentIdCard card = new StudentIdCard();
    card.setCardNumber(cardNumber);
    card.setStudent(student);
    student.setStudentIdCard(card);

    try {
      return cardRepo.save(card);
    } catch (DataIntegrityViolationException ex) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Card number already in use");
    }
  }

  @Override
  public StudentIdCard getById(Long id) {
    return cardRepo
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentIdCard not found"));
  }

  @Override
  public StudentIdCard getByStudentId(Long studentId) {
    Student student =
        studentRepo
            .findById(studentId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    StudentIdCard card = student.getStudentIdCard();
    if (card == null) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "StudentIdCard for student not found");
    }
    return card;
  }

  @Override
  @Transactional
  public StudentIdCard updateCardNumber(Long id, String cardNumber) {
    StudentIdCard card =
        cardRepo
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentIdCard not found"));
    card.setCardNumber(cardNumber);
    try {
      return cardRepo.save(card);
    } catch (DataIntegrityViolationException ex) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Card number already in use");
    }
  }

  @Override
  @Transactional
  public void delete(Long id) {
    StudentIdCard card =
        cardRepo
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentIdCard not found"));

    // Break the relationship from the student side to maintain consistency
    Student student = card.getStudent();
    if (student != null) {
      student.setStudentIdCard(null);
    }

    cardRepo.delete(card);
  }
}
