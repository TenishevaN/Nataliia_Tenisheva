package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.ReviewDto;
import com.epam.spring.homework.mvc.repairAgency.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/review")
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {

        ReviewDto review = reviewService.add(reviewDto);
        if (review == null) {
            throw new NullPointerException();
        }
        return review;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/review")
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/review/{id}")
    public List<ReviewDto> getAllByRequestId(@PathVariable Long id) {
        return reviewService.getAllByRequestId(id);
    }
}
