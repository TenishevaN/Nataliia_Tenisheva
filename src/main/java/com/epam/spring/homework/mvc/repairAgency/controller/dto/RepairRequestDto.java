package com.epam.spring.homework.mvc.repairAgency.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class RepairRequestDto {

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
