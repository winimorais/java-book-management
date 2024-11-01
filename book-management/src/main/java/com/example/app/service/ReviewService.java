package com.example.app.service;


import com.example.app.model.Book;
import com.example.app.model.Review;

import java.util.List;

public interface ReviewService {

    Review save(Review review);

    List<Review> findByBook(Book book);
}
