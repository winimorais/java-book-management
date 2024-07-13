package com.example.app.repository;

import com.example.app.model.Book;
import com.example.app.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByBook(Book book);
}
