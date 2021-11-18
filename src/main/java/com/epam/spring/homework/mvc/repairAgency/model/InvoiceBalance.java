package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InvoiceBalance {

    private int id;
    private int invoiceId;
    private int repairRequestId;
    private BigDecimal ammount;
}
