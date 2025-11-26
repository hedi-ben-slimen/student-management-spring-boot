package com.info.student.service;

import com.info.student.model.StudentIdCard;

public interface StudentIdCardService {
  StudentIdCard create(Long studentId, String cardNumber);

  StudentIdCard getById(Long id);

  StudentIdCard getByStudentId(Long studentId);

  StudentIdCard updateCardNumber(Long id, String cardNumber);

  void delete(Long id);
}
