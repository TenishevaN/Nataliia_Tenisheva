package com.epam.spring.homework.mvc.repairAgency.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String name;
    private String email;
    private transient String password;
    @Column(nullable = true, name = "role_id")
    private Integer roleId;
    @Column(nullable = true, name = "invoice_id")
    private Integer invoiceId;
}