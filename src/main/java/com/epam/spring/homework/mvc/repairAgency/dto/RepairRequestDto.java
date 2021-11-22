package com.epam.spring.homework.mvc.repairAgency.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class RepairRequestDto {

    private Long id;
    private Long userId;
    private String userName;
    private Long statusId;
    private Long masterId;
    private String masterName;
    private BigDecimal cost;
    private Instant date;

    @NotBlank
    private String description;
}
