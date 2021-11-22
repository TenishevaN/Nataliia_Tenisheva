package com.epam.spring.homework.mvc.repairAgency.service;

import com.epam.spring.homework.mvc.repairAgency.dto.AccountLocalizationDto;

import java.util.List;

public interface AccountLocalizationService {

    AccountLocalizationDto add(AccountLocalizationDto accountLocalizationDto);

    int update(final Long accountId, final Long languageId, String name);

    List<AccountLocalizationDto> getAllByUserId(final Long userId);

    AccountLocalizationDto get(final Long userId, final Long languageId);
}
