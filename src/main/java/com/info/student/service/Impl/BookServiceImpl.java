package com.info.student.service.Impl;

import com.info.student.model.Book;
import com.info.student.model.Student;
import com.info.student.repository.BookRepository;
import com.info.student.repository.StudentRepository;
import com.info.student.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookServiceImpl(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Book create(String bookName, LocalDateTime bookDate, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        Book book = new Book();
        book.setBookName(bookName);
        book.setBookDate(bookDate);
        book.setStudent(student);
        
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book update(Long id, String bookName, LocalDateTime bookDate, Long studentId) {
        Book book = getById(id);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        book.setBookName(bookName);
        book.setBookDate(bookDate);
        book.setStudent(student);
        
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = getById(id);
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getBooksByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return student.getBooks();
    }
}