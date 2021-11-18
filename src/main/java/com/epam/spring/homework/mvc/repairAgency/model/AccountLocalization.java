package com.epam.spring.homework.mvc.repairAgency.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountLocalization {

    private int id;
    private int account_id;
    private int language_id;
    private String name;
}
