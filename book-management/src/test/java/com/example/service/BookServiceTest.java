package com.example.service;

import com.example.app.model.Book;
import com.example.app.repository.BookRepository;
import com.example.app.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Book book = new Book();
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.save(book);

        assertNotNull(savedBook);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testFindAll() {
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        String id = "550e8400-e29b-41d4-a716-446655440000";
        Book book = new Book();
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(book, result.get());
        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        String id = "550e8400-e29b-41d4-a716-446655440000";
        when(bookRepository.findById("550e8400-e29b-41d4-a716-446655440000")).thenReturn(Optional.empty());

        Optional<Book> result = bookService.findById(id);

        assertFalse(result.isPresent());
        verify(bookRepository, times(1)).findById(id);
    }
}
