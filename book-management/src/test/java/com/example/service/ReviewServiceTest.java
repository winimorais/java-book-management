package com.example.service;

import com.example.app.model.Book;
import com.example.app.model.Review;
import com.example.app.repository.ReviewRepository;
import com.example.app.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Review review = new Review();
        when(reviewRepository.save(review)).thenReturn(review);

        Review savedReview = reviewService.save(review);

        assertNotNull(savedReview);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testFindByBook() {
        Book book = new Book();
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewRepository.findByBook(book)).thenReturn(reviews);

        List<Review> result = reviewService.findByBook(book);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reviewRepository, times(1)).findByBook(book);
    }
}
