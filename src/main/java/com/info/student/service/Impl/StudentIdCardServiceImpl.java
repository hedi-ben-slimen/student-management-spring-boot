package com.info.student.service.Impl;

import com.info.student.Exception.RessourceNotFoundException;
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

import java.util.List;

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
    public StudentIdCard create(String cardNumber, Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RessourceNotFoundException("Student not found"));
        
        if (cardRepo.existsByStudent(student)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already has an ID card");
        }

        StudentIdCard card = new StudentIdCard();
        card.setCardNumber(cardNumber);
        card.setStudent(student);
        
        try {
            return cardRepo.save(card);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Card number already in use");
        }
    }

    @Override
    public StudentIdCard getById(Long id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student ID card not found"));
    }

    @Override
    public StudentIdCard getByStudentId(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return cardRepo.findByStudent(student)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student ID card not found"));
    }

    @Override
    public List<StudentIdCard> list() {
        return cardRepo.findAll();
    }

    @Override
    @Transactional
    public StudentIdCard update(Long id, String cardNumber) {
        StudentIdCard card = getById(id);
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
        StudentIdCard card = getById(id);
        cardRepo.delete(card);
    }
}