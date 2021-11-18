package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class RepairRequest {

    private int id;
    private int userId;
    private String userName;
    private int statusId;
    private String statusName;
    private Integer masterId;
    private String masterName;
    private BigDecimal cost;
    private Instant date;
    private String description;
}
