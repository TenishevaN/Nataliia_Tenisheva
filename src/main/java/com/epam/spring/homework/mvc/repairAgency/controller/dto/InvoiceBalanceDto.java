package com.epam.spring.homework.mvc.repairAgency.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceBalanceDto {

    private int invoiceId;
    private int repairRequestId;
    private BigDecimal ammount;
}
