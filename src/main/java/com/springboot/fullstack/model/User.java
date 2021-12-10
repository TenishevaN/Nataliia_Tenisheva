package com.springboot.fullstack.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "account")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String name;

    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 128)
    private String password;
    @Column(nullable = true, name = "role_id")
    private Integer roleId;
    @Column(nullable = true, name = "invoice_id")
    private Integer invoiceId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<RepairRequest> repairRequests;

    public User(String login) {
        this.login = login;
    }

    public User() {

    }
}