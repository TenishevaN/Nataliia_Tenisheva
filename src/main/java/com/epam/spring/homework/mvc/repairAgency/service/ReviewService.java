package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto add(ReviewDto reviewDto);
    List<ReviewDto> getAll();
    List<ReviewDto> getAllByRequestId(Long id);

}
