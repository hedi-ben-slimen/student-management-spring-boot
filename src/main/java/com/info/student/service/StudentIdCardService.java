package com.info.student.service;

import com.info.student.model.StudentIdCard;
import java.util.List;

public interface StudentIdCardService {
    StudentIdCard create(String cardNumber, Long studentId);
    StudentIdCard getById(Long id);
    StudentIdCard getByStudentId(Long studentId);
    List<StudentIdCard> list();
    StudentIdCard update(Long id, String cardNumber);
    void delete(Long id);
}