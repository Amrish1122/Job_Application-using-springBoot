package com.example.Practical1.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId,Review review);
    Review getReview(Long companyId, Long reviewId);
    boolean updatedReview(Long companyId,Long reviewId,Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
