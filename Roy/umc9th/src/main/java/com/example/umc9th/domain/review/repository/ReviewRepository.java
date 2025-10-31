package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r " +
            "LEFT JOIN FETCH r.reviewPhotos " +
            "WHERE r.store.id = :storeId")
    List<Review> findReviewsWithPhotosByStoreId(@Param("storeId") Long storeId);
}