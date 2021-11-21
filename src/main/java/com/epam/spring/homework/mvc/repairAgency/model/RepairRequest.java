package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "repair_request")
public class RepairRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true, name = "account_id")
    private Long userId;
    @Column(nullable = true, name = "status_id")
    private Long statusId;
    @Column(nullable = true, name = "master_id")
    private Long masterId;
    private BigDecimal cost;
    private Instant date;
    private String description;
}
