package com.epam.spring.homework.mvc.repairAgency.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.model.Review;
import com.epam.spring.homework.mvc.repairAgency.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReviewRepositoryImpl implements ReviewRepository, GenerationId {

    private final List<Review> list = new ArrayList<>();

    @Override
    public Review add(Review review) {

         if (review.getId() == 0) {
             List<Integer> idList = list
                     .stream()
                     .map(x -> x.getId()).collect(Collectors.toList());
             int id = generateId(idList);
             log.info("id for new  review {}", id);
            review.setId(id);
        }
        review.setDate(Instant.now());
        list.add(review);
        return review;
    }

    @Override
    public List<Review> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public List<Review> getAllByRequestId(final int id) {
        return (List<Review>) list.stream()
                .filter(review -> (review.getId() == id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Review is not found!"));
    }
}
