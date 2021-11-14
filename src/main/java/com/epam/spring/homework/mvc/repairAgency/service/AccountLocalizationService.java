package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.AccountLocalizationDto;

import java.util.List;

public interface AccountLocalizationService {

    AccountLocalizationDto add(AccountLocalizationDto accountLocalizationDto);

    AccountLocalizationDto update(int id, AccountLocalizationDto accountLocalizationDto);

    List<AccountLocalizationDto> get(int id);

    AccountLocalizationDto get(int userId, int languageId);
}
