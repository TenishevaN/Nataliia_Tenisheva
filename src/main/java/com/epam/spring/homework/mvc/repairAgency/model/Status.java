package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "status_localization")
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
