package com.example.app.controller;

import com.example.app.model.Book;
import com.example.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        log.info("Request: " + book);
        return bookService.save(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable String id) {
        log.info("Request: " + id);
        return bookService.findById(id);
    }
}