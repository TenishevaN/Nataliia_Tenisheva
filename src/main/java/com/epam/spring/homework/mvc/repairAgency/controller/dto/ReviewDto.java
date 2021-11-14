package com.epam.spring.homework.mvc.repairAgency.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ReviewDto {

    private Instant date;
    private String comment;
}
