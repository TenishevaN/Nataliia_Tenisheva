package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class Review {

    private Instant date;
    private String comment;
    private int id;
    private int repair_request_id;

}
