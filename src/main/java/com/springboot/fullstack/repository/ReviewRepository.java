package com.springboot.fullstack.repository;

import com.springboot.fullstack.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);

    List<Review> findAll();

    List<Review> findAllByRepairRequestId(Long id);
}
