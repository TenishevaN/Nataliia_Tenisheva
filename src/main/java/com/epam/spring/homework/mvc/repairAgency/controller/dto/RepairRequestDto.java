package com.epam.spring.homework.mvc.repairAgency.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class RepairRequestDto {

    @NotBlank
    private int userId;
    private String userName;
    private int statusId;
    private String statusName;
    private int masterId;
    private String masterName;
    private BigDecimal cost;
    private Instant date;

    @NotBlank
    private String description;
}
