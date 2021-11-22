package com.epam.spring.homework.mvc.repairAgency.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AccountLocalizationDto {

    private Long id;
    private Long accountId;
    private Long languageId;
    private String name;
}
