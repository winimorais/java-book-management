package com.example.app.service;

import com.example.app.model.Book;
import com.example.app.model.Review;
import com.example.app.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findByBook(Book book) {
        return reviewRepository.findByBook(book);
    }

}