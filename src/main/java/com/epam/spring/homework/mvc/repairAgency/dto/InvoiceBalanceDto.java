package com.epam.spring.homework.mvc.repairAgency.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class InvoiceBalanceDto {

    private int invoiceId;
    private int repairRequestId;

    @Digits(integer = 10, fraction = 2)
    @NotNull
    private BigDecimal ammount;
}
