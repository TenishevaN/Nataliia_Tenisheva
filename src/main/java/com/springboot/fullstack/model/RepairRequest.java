package com.springboot.fullstack.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private User user;

    @Column(name = "status_id")
    private Long statusId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id", nullable = true)
    private User master;

    private BigDecimal cost;
    @Column(nullable = true, name = "date")
    private Instant date;
    private String description;
}
