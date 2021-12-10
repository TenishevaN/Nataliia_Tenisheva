package com.springboot.fullstack.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class InvoiceDto {

    private Long id;
    private int accountId;

    @Digits(integer = 10, fraction = 2)
    @NotNull
    private BigDecimal ammount;
}
