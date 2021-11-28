package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.ReviewDto;
import com.epam.spring.homework.mvc.repairAgency.mapper.ReviewMapper;
import com.epam.spring.homework.mvc.repairAgency.model.Review;
import com.epam.spring.homework.mvc.repairAgency.repository.ReviewRepository;
import com.epam.spring.homework.mvc.repairAgency.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Spy
    private ReviewMapper reviewMapper = Mappers.getMapper(ReviewMapper.class);

    @Test
    void getAllReviewByRequestIdTest() {

        //given
        when(reviewRepository.findAllByRepairRequestId(any(Long.class))).thenReturn(Arrays.asList(new Review(), new Review()));

        //when
        List<ReviewDto> actualReviewList = reviewService.getAllByRequestId(1L);

        //then
        assertThat(actualReviewList, hasSize(2));
    }

    @Test
    void addReviewTest() {

        //given
        Review expectedReview = new Review();
        expectedReview.setComment("comment");
        when(reviewRepository.save((any(Review.class)))).thenReturn(expectedReview);

        //when
        ReviewDto actualReview = reviewService.add(reviewMapper.mapReviewDto(expectedReview));

        //then
        assertEquals(expectedReview.getComment(), actualReview.getComment());
    }

    @Test
    void getAllReviewTest() {

        when(reviewRepository.findAll()).thenReturn(Arrays.asList(new Review(), new Review()));

        List<ReviewDto> data = reviewService.getAll();

        assertEquals(2, data.size());
    }
}
