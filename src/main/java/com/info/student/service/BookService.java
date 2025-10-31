package com.info.student.service;

import com.info.student.model.Book;
import java.time.LocalDateTime;
import java.util.List;

public interface BookService {
    Book create(String bookName, LocalDateTime bookDate, Long studentId);
    Book getById(Long id);
    List<Book> list();
    Book update(Long id, String bookName, LocalDateTime bookDate, Long studentId);
    void delete(Long id);
    List<Book> getBooksByStudentId(Long studentId);
}