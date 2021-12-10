package com.springboot.fullstack.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_localization")
public class AccountLocalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "language_id")
    private Long languageId;
    private String name;
}
