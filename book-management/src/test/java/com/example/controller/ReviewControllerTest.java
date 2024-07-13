package com.example.controller;

import com.example.app.controller.ReviewController;
import com.example.app.model.Book;
import com.example.app.model.Review;
import com.example.app.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReview() {
        Review review = new Review();
        review.setId("550e8400-e29b-41d4-a716-446655440000");
        review.setContent("Great book!");
        review.setRating(5);

        when(reviewService.save(any(Review.class))).thenReturn(review);

        Review savedReview = reviewController.addReview(review);

        assertNotNull(savedReview);
        assertEquals("Great book!", savedReview.getContent());
        assertEquals(5, savedReview.getRating());
        verify(reviewService, times(1)).save(review);
    }

    @Test
    void testGetReviewsByBook() {
        String bookId = "550e8400-e29b-41d4-a716-446655440000";
        Book book = new Book();
        book.setId(bookId);

        Review review1 = new Review();
        review1.setId("550e8400-e29b-41d4-a716-446655440000");
        review1.setContent("Amazing read!");
        review1.setRating(5);
        review1.setBook(book);

        Review review2 = new Review();
        review2.setId("550e8400-e29b-41d4-a716-446655440000");
        review2.setContent("Highly recommended!");
        review2.setRating(4);
        review2.setBook(book);

        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewService.findByBook(any(Book.class))).thenReturn(reviews);

        List<Review> result = reviewController.getReviewsByBook(bookId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Amazing read!", result.get(0).getContent());
        assertEquals(5, result.get(0).getRating());
        assertEquals("Highly recommended!", result.get(1).getContent());
        assertEquals(4, result.get(1).getRating());
        verify(reviewService, times(1)).findByBook(any(Book.class));
    }
}
