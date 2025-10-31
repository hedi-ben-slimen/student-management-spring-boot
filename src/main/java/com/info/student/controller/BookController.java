package com.info.student.controller;

import com.info.student.model.Book;
import com.info.student.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book, @RequestParam Long studentId) {
        return service.create(book.getBookName(), book.getBookDate(), studentId);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Book> list() {
        return service.list();
    }

    @GetMapping("/by-student/{studentId}")
    public List<Book> getBooksByStudentId(@PathVariable Long studentId) {
        return service.getBooksByStudentId(studentId);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book, @RequestParam Long studentId) {
        return service.update(id, book.getBookName(), book.getBookDate(), studentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}