package com.epam.spring.homework.mvc.repairAgency.service.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Invoice {

    private int id;
    private int account_id;
    private BigDecimal ammount;
}
