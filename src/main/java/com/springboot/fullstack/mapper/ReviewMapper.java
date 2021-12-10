package com.springboot.fullstack.mapper;


import com.springboot.fullstack.dto.ReviewDto;
import com.springboot.fullstack.model.Review;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {

    ReviewDto mapReviewDto(Review reviews);
    Review mapReview(ReviewDto dto);
}