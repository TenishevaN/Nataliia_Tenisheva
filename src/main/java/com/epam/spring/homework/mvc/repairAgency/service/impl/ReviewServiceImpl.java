package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.ReviewDto;
import com.epam.spring.homework.mvc.repairAgency.service.ReviewService;
import com.epam.spring.homework.mvc.repairAgency.model.Review;
import com.epam.spring.homework.mvc.repairAgency.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto add(final ReviewDto reviewDto) {

        Review review = mapReviewDtoToReview(reviewDto);
        review = reviewRepository.add(review);
        return mapReviewToReviewDto(review);
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewRepository.getAll()
                .stream()
                .map(this::mapReviewToReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllByRequestId(final int id) {
        return reviewRepository.getAllByRequestId(id)
                .stream()
                .map(this::mapReviewToReviewDto)
                .collect(Collectors.toList());
    }

    private ReviewDto mapReviewToReviewDto(Review review) {
        return ReviewDto.builder()
                .date(review.getDate())
                .comment(review.getComment())
                .build();
    }

    private Review mapReviewDtoToReview(ReviewDto reviewDto) {
        return Review.builder()
                .date(reviewDto.getDate())
                .comment(reviewDto.getComment())
                .build();
    }
}
