package com.epam.spring.homework.mvc.repairAgency.service.repository.impl;

import com.epam.spring.homework.mvc.repairAgency.service.model.AccountLocalization;
import com.epam.spring.homework.mvc.repairAgency.service.repository.AccountLocalizationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountLocalizationRepositoryImpl implements AccountLocalizationRepository {

    private final List<AccountLocalization> list = new ArrayList<>();


    @Override
    public AccountLocalization add(final AccountLocalization accountLocalization) {
        list.add(accountLocalization);
        return accountLocalization;
    }

    @Override
    public AccountLocalization update(final int id, final AccountLocalization accountLocalization) {
        boolean isDeleted = list.removeIf(item -> item.getId() == id);
        if (isDeleted) {
            list.add(accountLocalization);
        } else {
            throw new RuntimeException("accountLocalization is not found!");
        }
        return accountLocalization;
    }

    @Override
    public List<AccountLocalization> get(final int id) {
        return (List<AccountLocalization>) list.stream()
                .filter(item -> item.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("accountLocalization is not found!"));
    }

    @Override
    public AccountLocalization get(final int userId, final int languageId) {
        return list.stream()
                .filter(item -> ((item.getAccount_id() == userId && item.getLanguage_id() == languageId)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("accountLocalization is not found!"));
    }
}
