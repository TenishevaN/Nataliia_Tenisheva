package com.springboot.fullstack.service;

import com.springboot.fullstack.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto add(ReviewDto reviewDto);
    List<ReviewDto> getAll();
    List<ReviewDto> getAllByRequestId(Long id);

}
