package com.epam.spring.homework.mvc.repairAgency.service.impl;

import com.epam.spring.homework.mvc.repairAgency.controller.dto.AccountLocalizationDto;
import com.epam.spring.homework.mvc.repairAgency.service.AccountLocalizationService;
import com.epam.spring.homework.mvc.repairAgency.service.model.AccountLocalization;
import com.epam.spring.homework.mvc.repairAgency.service.repository.AccountLocalizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountLocalizationServiceImpl implements AccountLocalizationService {

    private final AccountLocalizationRepository accountLocalizationRepository;

    @Override
    public AccountLocalizationDto add(final AccountLocalizationDto accountLocalizationDto) {
        AccountLocalization accountLocalization = mapAccountLocalizationDtoToAccountLocalization(accountLocalizationDto);
        accountLocalization = accountLocalizationRepository.add(accountLocalization);
        return mapAccountLocalizationToAccountLocalizationDto(accountLocalization);
    }

    @Override
    public AccountLocalizationDto update(final int id, final AccountLocalizationDto accountLocalizationDto) {
        AccountLocalization accountLocalization = mapAccountLocalizationDtoToAccountLocalization(accountLocalizationDto);
        accountLocalization = accountLocalizationRepository.update(id, accountLocalization);
        return mapAccountLocalizationToAccountLocalizationDto(accountLocalization);
    }

    @Override
    public List<AccountLocalizationDto> get(final int id) {
        return accountLocalizationRepository.get(id)
                .stream()
                .map(this::mapAccountLocalizationToAccountLocalizationDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountLocalizationDto get(final int userId, final int languageId) {
        AccountLocalization accountLocalization = accountLocalizationRepository.get(userId, languageId);
        return mapAccountLocalizationToAccountLocalizationDto(accountLocalization);
    }

    private AccountLocalizationDto mapAccountLocalizationToAccountLocalizationDto(final AccountLocalization accountLocalization) {
        return AccountLocalizationDto.builder()
                .name(accountLocalization.getName())
                .build();
    }

    private AccountLocalization mapAccountLocalizationDtoToAccountLocalization(final AccountLocalizationDto accountLocalizationDto) {
        return AccountLocalization.builder()
                .name(accountLocalizationDto.getName())
                .build();
    }
}
