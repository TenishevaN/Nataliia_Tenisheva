package com.epam.spring.homework.mvc.repairAgency.mapper;

import com.epam.spring.homework.mvc.repairAgency.dto.ReviewDto;
import com.epam.spring.homework.mvc.repairAgency.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    Set<ReviewDto> mapReviewDtos(Set<Review> reviews);
    ReviewDto mapReviewDto(Review reviews);
    Review mapReview(ReviewDto dto);
}