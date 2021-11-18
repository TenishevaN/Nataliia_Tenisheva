package com.epam.spring.homework.mvc.repairAgency.repository;

import com.epam.spring.homework.mvc.repairAgency.model.Review;

import java.util.List;

public interface ReviewRepository {

    Review add(Review review);

    List<Review> getAll();

    List<Review> getAllByRequestId(int id);
}
