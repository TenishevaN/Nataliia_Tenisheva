package com.epam.spring.homework.mvc.repairAgency.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {

    private int id;
    private String locale;
    private String name;
}
