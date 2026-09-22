package com.example.Distributed.Application.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {
    List<ReviewModel> findByProductId(Long productId);
}