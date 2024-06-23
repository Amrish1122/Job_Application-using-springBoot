package com.example.Practical1.review.impl;

import com.example.Practical1.company.Company;
import com.example.Practical1.company.CompanyService;
import com.example.Practical1.review.Review;
import com.example.Practical1.review.ReviewRepository;
import com.example.Practical1.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyservice;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService, CompanyService companyservice) {
        this.reviewRepository = reviewRepository;
        this.companyservice = companyservice;

    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyservice.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updatedReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyservice.getCompanyById(companyId)!=null){
            updatedReview.setCompany(companyservice.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyservice.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyservice.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;

        }
        else
        return false;
    }

}
