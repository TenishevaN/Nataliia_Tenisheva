package com.springboot.fullstack.service.implementation;

import com.springboot.fullstack.dto.ReviewDto;
import com.springboot.fullstack.mapper.ReviewMapper;
import com.springboot.fullstack.model.Review;
import com.springboot.fullstack.repository.ReviewRepository;
import com.springboot.fullstack.service.ReviewService;
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
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto add(final ReviewDto reviewDto) {

        Review review = reviewMapper.mapReview(reviewDto);
        review = reviewRepository.save(review);
        return reviewMapper.mapReviewDto(review);
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::mapReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllByRequestId(final Long id) {
        return reviewRepository.findAllByRepairRequestId(id)
                .stream()
                .map(reviewMapper::mapReviewDto)
                .collect(Collectors.toList());
    }
}
