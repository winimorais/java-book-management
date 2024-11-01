package com.example.app.controller;

import com.example.app.model.Book;
import com.example.app.model.Review;
import com.example.app.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        log.info("Request: " + review);
        return reviewService.save(review);
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable String bookId) {
        log.info("Request: " + bookId);
        Book book = new Book();
        book.setId(bookId);
        return reviewService.findByBook(book);
    }
}