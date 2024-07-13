package com.example.controller;

import com.example.app.controller.BookController;
import com.example.app.model.Book;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setId(" 550e8400-e29b-41d4-a716-446655440000");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");

        when(bookService.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookController.addBook(book);

        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals("Test Author", savedBook.getAuthor());
        assertEquals("1234567890", savedBook.getIsbn());
        verify(bookService, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book();
        book1.setId("550e8400-e29b-41d4-a716-446655440000");
        book1.setTitle("Book One");
        book1.setAuthor("Author One");
        book1.setIsbn("1111111111");

        Book book2 = new Book();
        book2.setId(" 550e8400-e29b-41d4-a716-446655440000");
        book2.setTitle("Book Two");
        book2.setAuthor("Author Two");
        book2.setIsbn("2222222222");

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.findAll()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Book One", result.get(0).getTitle());
        assertEquals("Book Two", result.get(1).getTitle());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void testGetBook() {
        String bookId = "550e8400-e29b-41d4-a716-446655440000";
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");

        when(bookService.findById(bookId)).thenReturn(Optional.of(book));

        Optional<Book> result = bookController.getBook(bookId);

        assertTrue(result.isPresent());
        assertEquals("Test Book", result.get().getTitle());
        assertEquals("Test Author", result.get().getAuthor());
        assertEquals("1234567890", result.get().getIsbn());
        verify(bookService, times(1)).findById(bookId);
    }

    @Test
    void testGetBook_NotFound() {
        String bookId = "550e8400-e29b-41d4-a716-446655440000";

        when(bookService.findById(bookId)).thenReturn(Optional.empty());

        Optional<Book> result = bookController.getBook(bookId);

        assertFalse(result.isPresent());
        verify(bookService, times(1)).findById(bookId);
    }
}
