package com.epam.spring.homework.mvc.repairAgency.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Builder
public class ReviewDto {

    private Long id;
    private Instant date;
    private Long repairRequestId;
    @NotBlank
    private String comment;
}
