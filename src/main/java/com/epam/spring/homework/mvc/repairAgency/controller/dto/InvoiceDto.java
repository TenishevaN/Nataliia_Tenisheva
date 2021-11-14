package com.epam.spring.homework.mvc.repairAgency.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceDto {

  private int account_id;
  private BigDecimal ammount;
}
