package com.example.app.service;


import com.example.app.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(String id);
}