package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "invoice_balance")
public class InvoiceBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "invoice_id")
    private Long invoiceId;
    @Column(name = "repair_request_id")
    private Long repairRequestId;
    private BigDecimal ammount;
}
