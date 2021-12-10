package com.springboot.fullstack.service.implementation;

import com.springboot.fullstack.dto.AccountLocalizationDto;
import com.springboot.fullstack.mapper.AccountLocalizationMapper;
import com.springboot.fullstack.model.AccountLocalization;
import com.springboot.fullstack.repository.AccountLocalizationRepository;
import com.springboot.fullstack.service.AccountLocalizationService;
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
    private final AccountLocalizationMapper accountLocalizationMapper;

    @Override
    public AccountLocalizationDto add(final AccountLocalizationDto accountLocalizationDto) {
        AccountLocalization accountLocalization = accountLocalizationMapper.mapAccountLocalization(accountLocalizationDto);
        accountLocalization = accountLocalizationRepository.save(accountLocalization);
        return accountLocalizationMapper.mapAccountLocalizationDto(accountLocalization);
    }

    @Override
    public int update(final Long accountId, final Long languageId, String name) {

        int result = accountLocalizationRepository.updateByAccountIdAndLanuageId(name, accountId, languageId);
        return result;

    }

    @Override
    public List<AccountLocalizationDto> getAllByUserId(final Long userId) {

        return accountLocalizationRepository.findByAccountId(userId)
                .stream()
                .map(accountLocalizationMapper::mapAccountLocalizationDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountLocalizationDto get(final Long userId, final Long languageId) {
        AccountLocalization accountLocalization = accountLocalizationRepository.findByAccountIdAndLanuageId(userId, languageId);
        return accountLocalizationMapper.mapAccountLocalizationDto(accountLocalization);
    }
}
