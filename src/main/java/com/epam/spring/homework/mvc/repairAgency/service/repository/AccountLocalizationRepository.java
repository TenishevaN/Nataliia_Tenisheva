package com.epam.spring.homework.mvc.repairAgency.service.repository;

import com.epam.spring.homework.mvc.repairAgency.service.model.AccountLocalization;

import java.util.List;

public interface AccountLocalizationRepository {

    AccountLocalization add(AccountLocalization accountLocalization);

    AccountLocalization update(int id, AccountLocalization accountLocalization);

    List<AccountLocalization> get(int id);

    AccountLocalization get(int userId, int languageId);
}
